package com.jass.authservice.controller

import com.jass.authservice.dto.ForgotPasswordRequest
import com.jass.authservice.service.controller_service.UserRecoveryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth/recovery")
class UserRecoveryController(
    private val userRecoveryService: UserRecoveryService
) {

    @PostMapping("/forgotPassword")
    fun forgotPassword(@RequestBody forgotPasswordRequest: ForgotPasswordRequest): ResponseEntity<Any> {
        return userRecoveryService.forgotPassword(forgotPasswordRequest)
    }

    @PostMapping("/forgotPassword/confirm")
    fun confirmChangePassword(@RequestParam email: String, @RequestParam token: String): ResponseEntity<Any> {
        return userRecoveryService.confirmChangePassword(email, token)
    }
}