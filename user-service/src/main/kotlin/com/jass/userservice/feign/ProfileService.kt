package com.jass.userservice.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "profile-service")
interface ProfileService {

    @PostMapping("/api/v1/profile/create")
    fun createProfile(@RequestParam email: String, @RequestParam userId: Int): ResponseEntity<HttpStatus>


//    Admin Controller

    @PostMapping("/api/v1/profile/admin/delete")
    fun deleteProfile(@RequestParam email: List<String>): ResponseEntity<Any>

    @PostMapping("/api/v1/profile/admin/deleteAll/except_email")
    fun deleteAllExceptEmail(@RequestParam email: List<String>): ResponseEntity<Any>

}