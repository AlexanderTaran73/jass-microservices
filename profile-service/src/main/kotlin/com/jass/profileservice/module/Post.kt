package com.jass.profileservice.module

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table
class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var text: String = ""

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()
}