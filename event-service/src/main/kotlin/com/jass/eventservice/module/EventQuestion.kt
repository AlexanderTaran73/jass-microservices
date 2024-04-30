package com.jass.eventservice.module

import com.jass.eventservice.module.type_dictionary.EventQuestionType
import jakarta.persistence.*

@Entity
@Table
class EventQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var question: String? = null

    @Column
    var answer: String? = null

    @Column
    var askedId: Int? = null

    @Column
    var responderId: Int? = null

    @ManyToOne
    @JoinColumn
    var type: EventQuestionType? = null

}