package com.jass.authservice.controller

import com.jass.authservice.dto.ChangePasswordRequest
import com.jass.authservice.service.controller_service.PasswordChangeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth/change-password")
class PasswordChangeController(
    private val passwordChangeService: PasswordChangeService
) {

    @PostMapping
    fun changePassword(@RequestBody changePasswordRequest: ChangePasswordRequest): ResponseEntity<Any> {
        return passwordChangeService.changePassword(changePasswordRequest)
    }

    @PostMapping("/confirm")
    fun confirmChangePassword(@RequestParam email: String, @RequestParam token: String): ResponseEntity<Any> {
        return passwordChangeService.confirmChangePassword(email, token)
    }
}