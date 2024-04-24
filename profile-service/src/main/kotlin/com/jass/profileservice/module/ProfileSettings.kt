package com.jass.profileservice.module

import jakarta.persistence.*

@Entity
@Table
class ProfileSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

//    TODO: add profile settings
}