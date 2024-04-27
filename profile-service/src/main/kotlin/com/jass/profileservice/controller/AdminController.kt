package com.jass.profileservice.controller

import com.jass.profileservice.module.Profile
import com.jass.profileservice.service.controller_service.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/profile/admin")
class AdminController(
    private val adminService: AdminService
) {

    @DeleteMapping("/delete")
    fun deleteProfile(@RequestParam email: List<String>): ResponseEntity<Any> {
        return adminService.deleteProfile(email)
    }

    @GetMapping("/getAll")
    fun getAll(): ResponseEntity<List<Profile>> {
        return adminService.getAll()
    }

    @DeleteMapping("/deleteAll/except_email")
    fun deleteAllExceptEmail(@RequestParam email: List<String>): ResponseEntity<Any> {
        return adminService.deleteAllExceptEmail(email)
    }
}