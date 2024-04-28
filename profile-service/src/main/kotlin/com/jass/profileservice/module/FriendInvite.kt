package com.jass.profileservice.module

import jakarta.persistence.*

@Entity
@Table
class FriendInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var inviterId: Int = 0

    @Column
    var invitedId: Int = 0

    @ManyToOne
    @JoinColumn
    var status: FriendInviteStatus? = null
}