package com.jass.userservice.controller

import com.jass.userservice.dto.CreateUserRequest
import com.jass.userservice.dto.ShortUserResponse
import com.jass.userservice.service.controller_service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/user")
class UsersController(
    private val usersService: UsersService
) {

    @PostMapping("/create")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<HttpStatus> {
        return usersService.createUser(createUserRequest)
    }

    @GetMapping("/getUsers/short/byEmail")
    fun getUsersShortByEmail(@RequestParam email: List<String>): ResponseEntity<MutableList<ShortUserResponse?>> {
        return usersService.getUsersShortByEmail(email)
    }

    @GetMapping("/getUsers/short/byId")
    fun getUsersShortById(@RequestParam id: List<Int>): ResponseEntity<MutableList<ShortUserResponse?>> {
        return usersService.getUsersShortById(id)
    }
}

