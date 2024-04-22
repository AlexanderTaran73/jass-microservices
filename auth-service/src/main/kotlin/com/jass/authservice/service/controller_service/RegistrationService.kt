package com.jass.authservice.service.controller_service

import com.jass.authservice.config.jwt.JwtProvider
import com.jass.authservice.dto.AuthResponse
import com.jass.authservice.dto.RegistrationRequest
import com.jass.authservice.feign.EmailService
import com.jass.authservice.model.RegistrationData
import com.jass.authservice.model.User
import com.jass.authservice.model.UserRole
import com.jass.authservice.service.model_service.RegistrationDataService
import com.jass.authservice.service.model_service.RegistrationDataServiceImpl
import com.jass.authservice.service.model_service.UserRoleService
import com.jass.authservice.service.model_service.UserService
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
    private val userService: UserService,
    private val userRoleService: UserRoleService,
    private val jwtProvider: JwtProvider,
    private val emailService: EmailService
    ) {



    fun registration(registrationRequest: RegistrationRequest): ResponseEntity<Any> {

//        Is email already registered
        if (userService.findByEmail(registrationRequest.email) != null) return ResponseEntity(HttpStatus.NOT_FOUND)

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

            val user = User().also {
                it.email = registrationData.email
                it.password = registrationData.password

                val userRole = userRoleService.findById(0)!!
                it.roles.add(userRole)
            }

            registrationDataService.delete(registrationData)
            userService.save(user)

            return ResponseEntity(
                AuthResponse(
                    jwtProvider.generateAccessToken(user.email, user.roles.map { it.name }),
                    jwtProvider.generateRefreshToken(user.email)
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
        val tokenLength = 4
        val stringBuilder = StringBuilder()

        repeat(tokenLength) {
            val digit = random.nextInt(0, 10)
            stringBuilder.append(digit)
        }

        return stringBuilder.toString()
    }
}