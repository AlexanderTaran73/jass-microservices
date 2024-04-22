package com.jass.authservice.controller

import com.jass.authservice.dto.RegistrationRequest

import com.jass.authservice.service.controller_service.RegistrationService
import com.jass.authservice.service.model_service.RegistrationDataServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/auth/registration")
class RegistrationController(
    private val registrationService: RegistrationService,
    private val registrationDataService: RegistrationDataServiceImpl,

    ) {

    @PostMapping
    fun registration(@RequestBody registrationRequest: RegistrationRequest): ResponseEntity<Any> {
        return registrationService.registration(registrationRequest)
    }

    @PostMapping("/confirm")
    fun confirmRegistration(@RequestParam email: String, @RequestParam token: String): ResponseEntity<Any> {
        return registrationService.confirmRegistration(email, token)
    }
}