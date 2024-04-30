package com.jass.eventservice.module

import jakarta.persistence.*

@Entity
@Table
class EventDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @Column
    var description_text: String? = null // TODO: set max length

    @Column
    var location: String? = null

    @Column
    var data: String? = null

    @Column
    var time: String? = null

    @Column
    var participants_count: String? = null

}
