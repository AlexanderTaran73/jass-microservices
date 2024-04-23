package com.jass.userservice.controller

import com.jass.userservice.dto.ShortUserResponse
import com.jass.userservice.service.controller_service.ChangeUserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/user/changeUser")
class ChangeUserController(
    private val changeUserService: ChangeUserService
) {


    @PostMapping("/changePassword")
    fun changePassword(@RequestParam email: String, @RequestParam password: String): ResponseEntity<ShortUserResponse> {
        return changeUserService.changePassword(email, password)
    }
}