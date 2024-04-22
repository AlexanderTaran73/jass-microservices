package com.jass.authservice.controller

import com.jass.authservice.service.model_service.UserService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth/connection")
class ConnectionController(
    private val userService: UserService
) {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun testConnection(){}
}