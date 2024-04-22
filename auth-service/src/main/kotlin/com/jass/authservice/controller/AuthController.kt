package com.jass.authservice.controller

import com.jass.authservice.dto.AuthRequest
import com.jass.authservice.service.controller_service.AuthService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping()
    fun auth(@RequestBody authRequest: AuthRequest): ResponseEntity<Any> {
        return authService.auth(authRequest)
    }

    @PostMapping("/refresh")
    fun refreshAuth(request: HttpServletRequest): ResponseEntity<Any> {
        return authService.refreshAuth(request)
    }
}