package com.jass.authservice.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "email-service")
interface EmailService {

    @PostMapping(value = ["/api/v1/email/send/emailToken"])
    fun sendEmailWithEmailToken(@RequestParam receiver: String, @RequestParam emailToken: String): ResponseEntity<String>
}