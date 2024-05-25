package com.jass.authservice.service.controller_service

import com.jass.authservice.config.jwt.JwtProvider
import com.jass.authservice.dto.AuthResponse
import com.jass.authservice.dto.CreateUserRequest
import com.jass.authservice.dto.RegistrationRequest
import com.jass.authservice.feign.EmailService
import com.jass.authservice.feign.UserService
import com.jass.authservice.model.AuthUserData
import com.jass.authservice.model.RegistrationData
import com.jass.authservice.service.model_service.AuthUserDataService
import com.jass.authservice.service.model_service.RegistrationDataService
import com.jass.authservice.service.model_service.RegistrationDataServiceImpl
import com.jass.authservice.service.model_service.UserRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random


@Service
class RegistrationService(
    private val registrationDataService: RegistrationDataService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
    private val emailService: EmailService,
    private val userService: UserService,
    private val authUserDataService: AuthUserDataService,
    private val userRoleService: UserRoleService
    ) {



    fun registration(registrationRequest: RegistrationRequest): ResponseEntity<Any> {

//        Is email already registered
        val authUserData = authUserDataService.findByEmail(registrationRequest.email)
        if (authUserData != null) return ResponseEntity(HttpStatus.NOT_FOUND)
        val users = userService.getUsersShort(listOf(registrationRequest.email)).body
        if (users!!.size > 0 && (users[0] != null || users[0]!!.status!!.name != "DELETED")) return ResponseEntity(HttpStatus.NOT_FOUND)

        val registrationData = registrationDataService.findByEmail(registrationRequest.email) ?: RegistrationData()
        registrationData.also {
            it.email = registrationRequest.email
            it.password = passwordEncoder.encode(registrationRequest.password)
            it.registration_token = generateRegistrationToken()
            it.createdAt = LocalDateTime.now()
        }

        emailService.sendEmailWithEmailToken(registrationRequest.email, registrationData.registration_token)

        registrationDataService.save(registrationData)
        return ResponseEntity(HttpStatus.CREATED)
    }



    fun confirmRegistration(email: String, token: String): ResponseEntity<Any> {

        val registrationData = registrationDataService.findByEmail(email) ?: return ResponseEntity.notFound().build()
        if (registrationData.registration_token == token) {

            var authUserData = authUserDataService.findByEmail(registrationData.email)
            if (authUserData!=null) return ResponseEntity.badRequest().build()
            authUserData = AuthUserData().also {
                it.email = registrationData.email
                it.password = registrationData.password
                it.roles.add(userRoleService.findById(0)!!) // ROLE_USER
            }

            if (userService.createUser(CreateUserRequest(registrationData.email, registrationData.password)).statusCode != HttpStatus.CREATED) return ResponseEntity(HttpStatus.BAD_REQUEST)
            registrationDataService.delete(registrationData)

            authUserDataService.save(authUserData)


            return ResponseEntity(
                AuthResponse(
                    jwtProvider.generateAccessToken(authUserData.email, authUserData.id, authUserData.roles.map { it.name }),
                    jwtProvider.generateRefreshToken(authUserData.email)
                ), HttpStatus.CREATED)
        }
        else {
            registrationData.registration_token = generateRegistrationToken()

            emailService.sendEmailWithEmailToken(registrationData.email, registrationData.registration_token)

            registrationDataService.save(registrationData)
            return ResponseEntity.badRequest().body("Invalid token! New token sent!")
        }
    }


    private fun generateRegistrationToken(): String {
        val random = Random.Default
        val tokenLength = 6
        val stringBuilder = StringBuilder()

        repeat(tokenLength) {
            val digit = random.nextInt(0, 10)
            stringBuilder.append(digit)
        }

        return stringBuilder.toString()
    }
}