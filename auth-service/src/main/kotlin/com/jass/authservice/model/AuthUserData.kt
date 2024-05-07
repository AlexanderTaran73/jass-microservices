package com.jass.authservice.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table
class AuthUserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column(unique = true)
    var email = ""

    @Column
    var password = ""

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable
    var roles: MutableList<UserRole> = mutableListOf()
}