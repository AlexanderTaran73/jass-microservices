package com.jass.profileservice.module

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
class ProfileColorTheme {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var name: String = ""
//    LIGHT
//    DARK
}