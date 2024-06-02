package com.jass.imageservice.model

import jakarta.persistence.*

@Entity
@Table
class ImageType {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var name: String = ""
//    ProfileImage,
//    ProfileAvatar,
//    EventImage,
//    EventAvatar
//    ProfilePostImage
}