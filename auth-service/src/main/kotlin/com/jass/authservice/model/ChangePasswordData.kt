package com.jass.authservice.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class ChangePasswordData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int = 0

    @Column(unique = true)
    var email = ""

    @Column
    var password = ""

    @Column
    var change_password_token = ""

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()

}