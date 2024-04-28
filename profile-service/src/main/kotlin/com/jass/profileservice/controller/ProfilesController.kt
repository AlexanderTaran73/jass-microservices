package com.jass.profileservice.controller

import com.jass.profileservice.dto.ProfileResponse
import com.jass.profileservice.service.controller_service.ProfilesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/profile")
class ProfilesController(
    private val profilesService: ProfilesService
) {

    @PostMapping("/create")
    fun createProfile(@RequestParam email: String): ResponseEntity<Any> {
        return profilesService.createProfile(email)
    }

    @GetMapping("/get/my_profile")
    fun getProfile(@RequestHeader("User-Email") email: String): ResponseEntity<Any> {
        return profilesService.getProfile(email)
    }

    @GetMapping("/get/all_profiles")
    fun getAllProfiles(@RequestHeader("User-Email") email: String): ResponseEntity<Any> {
        return profilesService.getAllProfiles(email)
    }

    @GetMapping("/get/profiles_by_ids")
    fun getProfilesByIds(@RequestHeader("User-Email") email: String, @RequestParam id: List<Int>): ResponseEntity<MutableList<ProfileResponse>> {
        return profilesService.getProfilesByIds(email, id)
    }
}