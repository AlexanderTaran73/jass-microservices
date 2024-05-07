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

}