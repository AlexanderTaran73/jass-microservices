package com.jass.eventservice.module.type_dictionary

import jakarta.persistence.*

@Entity
@Table
class EventType {
    @Id
    var id: Int = 0

    @Column(unique = true)
    var name: String = ""
//    Party,
//    Celebration,
//    Meeting,
//    CorporateParty,
//    Other

}