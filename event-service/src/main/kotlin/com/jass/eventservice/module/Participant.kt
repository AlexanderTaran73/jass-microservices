package com.jass.eventservice.module

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table
class Participant {
    @Id
    var id: Int = 0

    @Column
    var userId: Int = 0

//    TODO: add more Participant info
}