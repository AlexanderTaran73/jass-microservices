package com.jass.eventservice.module.type_dictionary

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
class AccessToEvent {
    @Id
    var id: Int = 0

    @Column
    var name: String = ""
//    Open,
//    ByRequest,
//    ByPayment
}