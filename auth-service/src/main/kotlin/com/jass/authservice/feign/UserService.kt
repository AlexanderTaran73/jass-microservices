package com.jass.authservice.feign

import com.jass.authservice.dto.CreateUserRequest
import com.jass.authservice.dto.ShortUserResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam


@FeignClient(name = "user-service")
interface UserService {

    @PostMapping("/api/v1/user/create")
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<ShortUserResponse?>

    @GetMapping("/api/v1/user/getUsers/short/")
    fun getUsersShort(@RequestParam email: List<String>): ResponseEntity<List<ShortUserResponse?>>

    @PostMapping("/api/v1/user/changeUser/changePassword")
    fun changePassword(@RequestParam email: String, @RequestParam password: String): ResponseEntity<ShortUserResponse>
}