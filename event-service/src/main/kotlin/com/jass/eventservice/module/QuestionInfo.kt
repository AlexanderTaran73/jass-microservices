package com.jass.eventservice.module

import jakarta.persistence.*


@Entity
@Table
class QuestionInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var requesterId: Int = 0

    @Column
    var title: String? = null

    @Column
    var text: String? = null

    @Column
    var last_updated: String? = null
}