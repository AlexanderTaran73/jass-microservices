package com.jass.profileservice.module

import jakarta.persistence.*

@Entity
@Table
class FriendInviteStatus {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var name: String = ""

//      SENT
//      ACCEPTED
//      REJECTED
}