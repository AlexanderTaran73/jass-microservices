package com.jass.profileservice.module

import jakarta.persistence.*


@Entity
@Table
class ImageType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var type: String = ""
}