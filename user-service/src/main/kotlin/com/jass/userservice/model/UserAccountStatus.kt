package com.jass.userservice.model

import jakarta.persistence.*


@Entity
@Table(name="user_role")
class UserAccountStatus {
    @Id
    var id: Int = 0

    @Column
    var name: String = ""
//    DEFAULT
//    BANNED
//    DELETED
}