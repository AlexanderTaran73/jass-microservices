package com.jass.eventservice.module

import com.jass.eventservice.module.type_dictionary.AccessToEvent
import com.jass.eventservice.module.type_dictionary.EventVisibility
import jakarta.persistence.*


@Entity
@Table
class EventSettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int? = null

    @ManyToOne
    @JoinColumn
    var eventVisibility: EventVisibility? = null

    @ManyToOne
    @JoinColumn
    var accessToEvent: AccessToEvent? = null

//    TODO: event code

}