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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable
    var status: UserAccountStatus? = null

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()
}