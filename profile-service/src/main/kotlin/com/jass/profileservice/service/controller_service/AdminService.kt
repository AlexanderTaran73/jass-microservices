package com.jass.profileservice.service.controller_service

import com.jass.profileservice.module.Profile
import com.jass.profileservice.service.model_service.ProfileService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val profileService: ProfileService
) {
    fun deleteProfile(email: List<String>): ResponseEntity<Any> {
        email.forEach {
            val profile = profileService.findByUserEmail(it)
            if (profile != null) profileService.delete(profile)
        }
        return ResponseEntity(HttpStatus.OK)
    }

    fun getAll(): ResponseEntity<List<Profile>> {
        return ResponseEntity(profileService.findAll(), HttpStatus.OK)
    }

    fun deleteAllExceptEmail(email: List<String>): ResponseEntity<Any> {
        profileService.findAll().forEach {
            if (it.userEmail !in email) profileService.delete(it)
        }
        return ResponseEntity(HttpStatus.OK)
    }
}