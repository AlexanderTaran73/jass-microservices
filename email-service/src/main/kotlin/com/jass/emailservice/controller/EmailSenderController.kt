package com.jass.emailservice.controller

import com.jass.emailservice.service.EmailService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/email")
class EmailSenderController(
    private val emailService: EmailService
) {

    @PostMapping("/send/emailToken")
    fun sendEmailWithEmailToken(@RequestParam receiver: String, @RequestParam emailToken: String): ResponseEntity<Any> {
        return emailService.sendEmailWithEmailToken(receiver, emailToken)
    }
}