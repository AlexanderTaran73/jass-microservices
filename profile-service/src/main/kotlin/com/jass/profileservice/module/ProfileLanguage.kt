package com.jass.profileservice.module

import jakarta.persistence.*

@Entity
@Table
class ProfileLanguage {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var name: String = ""
//    ENGLISH,
//    RUSSIAN
}