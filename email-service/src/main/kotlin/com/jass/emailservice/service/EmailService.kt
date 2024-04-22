package com.jass.emailservice.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service


@Service
class EmailService(
    private val javaMailSender : JavaMailSender,
    @Value("\${spring.mail.username}") private val senderEmail: String,
){
//  TODO: beautiful email
    fun sendEmailWithEmailToken(receiver: String, emailToken: String): ResponseEntity<Any> {
        val message = SimpleMailMessage()
        message.setFrom(senderEmail)
        message.setTo(receiver)
        message.setSubject("Email confirmation")
        message.setText(emailToken)
        javaMailSender.send(message)

        return ResponseEntity.ok("Email sent successfully")
    }
}