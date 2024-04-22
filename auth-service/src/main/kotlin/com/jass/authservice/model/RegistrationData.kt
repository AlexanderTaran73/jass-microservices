package com.jass.authservice.model

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class RegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0

    @Column(unique = true)
    var email = ""

    @Column
    var password = ""

    @Column
    var registration_token = ""

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()
}