package com.jass.userservice.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column(unique = true)
    var email = ""

    @Column
    var password = ""

//    TODO: move to authService
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable
    var roles: MutableList<UserRole> = mutableListOf()

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()
}