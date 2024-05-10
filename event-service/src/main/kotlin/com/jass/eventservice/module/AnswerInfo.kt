package com.jass.eventservice.module

import jakarta.persistence.*

@Entity
@Table
class AnswerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var responderId: Int = 0

    @Column
    var text: String? = null

    @Column
    var last_updated: String? = null
}