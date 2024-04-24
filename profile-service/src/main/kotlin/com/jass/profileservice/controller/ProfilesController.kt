package com.jass.profileservice.controller

import com.jass.profileservice.service.controller_service.ProfilesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/profile")
class ProfilesController(
    private val profilesService: ProfilesService
) {

    @PostMapping("/create")
    fun createProfile(@RequestParam email: String): ResponseEntity<Any> {
        return profilesService.createProfile(email)
    }
}