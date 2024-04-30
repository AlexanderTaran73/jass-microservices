package com.jass.eventservice.module

import jakarta.persistence.*

@Entity
@Table
class OrganizerActivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name: String = ""
}