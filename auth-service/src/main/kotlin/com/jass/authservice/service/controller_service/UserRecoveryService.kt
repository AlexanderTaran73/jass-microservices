package com.jass.authservice.service.controller_service

import com.jass.authservice.dto.ForgotPasswordRequest
import com.jass.authservice.feign.EmailService
import com.jass.authservice.feign.UserService
import com.jass.authservice.model.ChangePasswordData
import com.jass.authservice.service.model_service.AuthUserDataService
import com.jass.authservice.service.model_service.ChangePasswordDataService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserRecoveryService(
    private val passwordEncoder: PasswordEncoder,
    private val changePasswordDataService: ChangePasswordDataService,
    private val emailService: EmailService,
    private val passwordChangeService: PasswordChangeService,
    private val authUserDataService: AuthUserDataService
) {


    fun forgotPassword(forgotPasswordRequest: ForgotPasswordRequest): ResponseEntity<Any> {
        val authUserData = authUserDataService.findByEmail(forgotPasswordRequest.email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val changePasswordData = changePasswordDataService.findByEmail(forgotPasswordRequest.email) ?: ChangePasswordData()

        changePasswordData.also {
            it.email = authUserData.email
            it.password = passwordEncoder.encode(forgotPasswordRequest.newPassword)
            it.change_password_token = passwordChangeService.generateChangePasswordToken()
            it.createdAt = LocalDateTime.now()
        }

        emailService.sendEmailWithEmailToken(changePasswordData.email, changePasswordData.change_password_token)

        changePasswordDataService.save(changePasswordData)

        return ResponseEntity(HttpStatus.CREATED)
    }

    fun confirmChangePassword(email: String, token: String): ResponseEntity<Any> {
        return passwordChangeService.confirmChangePassword(email, token)
    }
}