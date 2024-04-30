package com.jass.eventservice.module

import jakarta.persistence.*

@Entity
@Table
class OrganizerContacts {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var email: String? = null

    @Column
    var phoneNumber: String? = null

    @Column
    var website: String? = null

    @Column
    var telegram: String? = null

    @Column
    var another_contact: String? = null
}