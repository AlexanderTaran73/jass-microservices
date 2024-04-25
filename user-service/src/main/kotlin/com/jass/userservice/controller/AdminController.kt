package com.jass.userservice.controller

import com.jass.userservice.service.controller_service.AdminService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/user/admin")
class AdminController(
    private val adminService: AdminService
) {

    @PostMapping("/delete")
    fun deleteUsers(@RequestParam email: List<String>): ResponseEntity<Any> {
        return adminService.deleteUsers(email)
    }

    @GetMapping("/getAll")
    fun getAll(): ResponseEntity<Any> {
        return adminService.getAll()
    }

    @PostMapping("/deleteAll/except_email")
    fun deleteAllExceptEmail(@RequestParam email: List<String>): ResponseEntity<Any> {
        return adminService.deleteAllExceptEmail(email)
    }
}