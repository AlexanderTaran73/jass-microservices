package com.jass.userservice.service.controller_service

import com.jass.userservice.feign.ProfileService
import com.jass.userservice.service.model_service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val userService: UserService,
    private val profileService: ProfileService
) {
    fun deleteUsers(email: List<String>): ResponseEntity<Any> {
        email.forEach {
            val user = userService.findByEmail(it)
            if (user != null) userService.delete(user)
        }
        profileService.deleteProfile(email)
        return ResponseEntity(HttpStatus.OK)
    }

    fun getAll(): ResponseEntity<Any> {
        return ResponseEntity(userService.findAll(), HttpStatus.OK)
    }

    fun deleteAllExceptEmail(email: List<String>): ResponseEntity<Any> {

        userService.findAll().forEach {
            if (it.email !in email) userService.delete(it)
        }
        profileService.deleteAllExceptEmail(email)
        return ResponseEntity(HttpStatus.OK)
    }
}