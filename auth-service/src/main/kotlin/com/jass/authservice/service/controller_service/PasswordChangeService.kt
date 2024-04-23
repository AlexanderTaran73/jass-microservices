package com.jass.authservice.service.controller_service

import com.jass.authservice.dto.ChangePasswordRequest
import com.jass.authservice.feign.EmailService
import com.jass.authservice.feign.UserService
import com.jass.authservice.model.ChangePasswordData
import com.jass.authservice.service.model_service.ChangePasswordDataService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class PasswordChangeService(
    private val passwordEncoder: PasswordEncoder,
    private val changePasswordDataService: ChangePasswordDataService,
    private val emailService: EmailService,
    private val userService: UserService
) {


    fun changePassword(changePasswordRequest: ChangePasswordRequest): ResponseEntity<Any> {
        val user = userService.getUsersShort(listOf(changePasswordRequest.email)).body!![0] ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        if (!passwordEncoder.matches(changePasswordRequest.oldPassword, user.password)) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val changePasswordData = changePasswordDataService.findByEmail(changePasswordRequest.email) ?: ChangePasswordData()

        changePasswordData.also {
            it.email = user.email
            it.password = passwordEncoder.encode(changePasswordRequest.newPassword)
            it.change_password_token = generateChangePasswordToken()
            it.createdAt = LocalDateTime.now()
        }

        emailService.sendEmailWithEmailToken(changePasswordData.email, changePasswordData.change_password_token)

        changePasswordDataService.save(changePasswordData)

        return ResponseEntity(HttpStatus.CREATED)
    }

    fun confirmChangePassword(email: String, token: String): ResponseEntity<Any> {

        val changePasswordData = changePasswordDataService.findByEmail(email) ?: return ResponseEntity.notFound().build()
        if (changePasswordData.change_password_token == token) {
            val user = userService.getUsersShort(listOf(email)).body!![0] ?: return ResponseEntity(HttpStatus.NOT_FOUND)

            val shortUserResponse = userService.changePassword(user.email, changePasswordData.password).body?: return ResponseEntity(HttpStatus.BAD_REQUEST)

            changePasswordDataService.delete(changePasswordData)
            return ResponseEntity(HttpStatus.OK)
        }
        changePasswordData.also {
            it.change_password_token = generateChangePasswordToken()
        }
        emailService.sendEmailWithEmailToken(changePasswordData.email, changePasswordData.change_password_token)
        changePasswordDataService.save(changePasswordData)

        return ResponseEntity.badRequest().body("Invalid token! New token sent!")
    }


    fun generateChangePasswordToken(): String {
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