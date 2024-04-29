package com.jass.imageservice.model

import jakarta.persistence.*

@Entity
@Table
class ImageInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var fileName: String = ""

    @ManyToOne
    @JoinColumn
    var type: ImageType? = null

    @Column
    var ownerId: Int = 0

    @Column
    var createdTime: String = ""
}