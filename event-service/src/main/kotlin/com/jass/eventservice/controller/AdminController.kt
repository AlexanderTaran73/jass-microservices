package com.jass.eventservice.controller

import com.jass.eventservice.service.controller_service.AdminService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/event/admin")
class AdminController(
    private val adminService: AdminService
) {

    @DeleteMapping("/delete/byId")
    @ResponseStatus(HttpStatus.CREATED)
    fun deleteEvent(@RequestParam id: List<Int>) {
        adminService.deleteEvent(id)
    }
    @DeleteMapping("/delete/allExceptId")
    @ResponseStatus(HttpStatus.CREATED)
    fun deleteAllExceptId(@RequestParam id: List<Int>) {
        adminService.deleteAllExceptId(id)
    }

}