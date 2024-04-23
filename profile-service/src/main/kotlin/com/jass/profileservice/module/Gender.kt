package com.jass.profileservice.module

import jakarta.persistence.*


@Entity
@Table
class Gender {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name: String = ""
}