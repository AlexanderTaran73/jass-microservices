package com.jass.authservice.service.controller_service

import com.jass.authservice.config.jwt.JwtProvider
import com.jass.authservice.dto.AuthRequest
import com.jass.authservice.dto.AuthResponse
import com.jass.authservice.feign.UserService
import com.jass.authservice.service.model_service.AuthUserDataService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthService(
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder,
    private val authUserDataService: AuthUserDataService
) {
    fun auth(authRequest: AuthRequest): ResponseEntity<Any> {

        val authUserData = authUserDataService.findByEmail(authRequest.email)!!
        if(!passwordEncoder.matches(authRequest.password, authUserData.password)) return ResponseEntity.badRequest().build()

        return ResponseEntity(
            AuthResponse(
                jwtProvider.generateAccessToken(authUserData.email, authUserData.id, authUserData.roles.map { it.name }),
                jwtProvider.generateRefreshToken(authUserData.email)
            ), HttpStatus.OK)
    }

    fun refreshAuth(request: HttpServletRequest): ResponseEntity<Any> {
        val cookies = request.cookies ?: arrayOf<Cookie>()
        val refreshTokenCookie = cookies.find { it.name == "refreshToken" } ?: return ResponseEntity.badRequest().build()

        val email = jwtProvider.refreshTokenVerification(refreshTokenCookie.value) ?: return ResponseEntity.badRequest().build()
        val authUserData = authUserDataService.findByEmail(email)!!

        return ResponseEntity(
            AuthResponse(
                jwtProvider.generateAccessToken(authUserData.email, authUserData.id, authUserData.roles.map { it.name }),
                refreshTokenCookie.value
            ), HttpStatus.OK)
    }
}