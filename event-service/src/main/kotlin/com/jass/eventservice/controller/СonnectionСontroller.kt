package com.jass.eventservice.controller


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/event/connection")
class ConnectionController{

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    fun testConnection(){}
}