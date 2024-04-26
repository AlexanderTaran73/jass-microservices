package com.jass.profileservice.module

import jakarta.persistence.*

// TODO: move to image-service
@Entity
@Table
class ImageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var url: String = ""

    @ManyToOne(optional = false)
    @JoinColumn
    var type: ImageType? = null
}