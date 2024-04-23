package com.jass.userservice.service.controller_service

import com.jass.userservice.dto.ShortUserResponse
import com.jass.userservice.service.model_service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class ChangeUserService(
    private val userService: UserService
) {
    fun changePassword(email: String, password: String): ResponseEntity<ShortUserResponse> {
        val user = userService.findByEmail(email) ?: return ResponseEntity(null, HttpStatus.NOT_FOUND)
        user.password = password
        userService.save(user)
        return ResponseEntity(ShortUserResponse(user.email, user.password, user.roles), HttpStatus.OK)
    }
}