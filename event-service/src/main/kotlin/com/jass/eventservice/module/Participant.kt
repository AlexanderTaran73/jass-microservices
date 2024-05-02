package com.jass.eventservice.module

import jakarta.persistence.*

@Entity
@Table
class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var userId: Int = 0

//    TODO: add more Participant info
}