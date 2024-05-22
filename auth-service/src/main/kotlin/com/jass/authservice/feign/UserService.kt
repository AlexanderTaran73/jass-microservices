package com.jass.authservice.feign

import com.jass.authservice.dto.CreateUserRequest
import com.jass.authservice.dto.ShortUserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@FeignClient(name = "user-service")
interface UserService {

    @PostMapping("/api/v1/user/create")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<HttpStatus>

    @GetMapping("/api/v1/user/getUsers/short/")
    fun getUsersShort(@RequestParam email: List<String>): ResponseEntity<List<ShortUserResponse?>>
}