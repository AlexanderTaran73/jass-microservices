package com.jass.profileservice.module

import jakarta.persistence.*


@Entity
@Table
class Gender {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var name: String = ""

//    UNDEFINED,
//    MALE,
//    FEMALE,
//    OTHER
}