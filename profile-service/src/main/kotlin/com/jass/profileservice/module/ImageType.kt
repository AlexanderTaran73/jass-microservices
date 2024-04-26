package com.jass.profileservice.module

import jakarta.persistence.*

// TODO: move to image-service
@Entity
@Table
class ImageType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name: String = ""
}