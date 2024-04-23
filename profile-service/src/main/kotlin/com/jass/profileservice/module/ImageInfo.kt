package com.jass.profileservice.module

import jakarta.persistence.*

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