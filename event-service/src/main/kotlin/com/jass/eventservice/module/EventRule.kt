package com.jass.eventservice.module

import jakarta.persistence.*

@Entity
@Table
class EventRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name: String? = null

    @Column
    var description: String? = null
}