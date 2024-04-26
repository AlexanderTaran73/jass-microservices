package com.jass.profileservice.module

import jakarta.persistence.*

@Entity
@Table
class ProfileVisibility {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var name: String = ""

//    PUBLIC,
//    FRIENDS_ONLY,
//    CLOSED
}
