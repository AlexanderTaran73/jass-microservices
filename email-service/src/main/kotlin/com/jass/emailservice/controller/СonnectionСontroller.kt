package com.jass.emailservice.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/email/connection")
class ConnectionController() {

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun testConnection(){}
}