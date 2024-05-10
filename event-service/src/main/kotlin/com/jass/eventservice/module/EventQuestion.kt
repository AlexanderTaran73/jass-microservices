package com.jass.eventservice.module

import com.jass.eventservice.module.type_dictionary.EventQuestionType
import jakarta.persistence.*

@Entity
@Table
class EventQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn
    var question: QuestionInfo? = null

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    var answers: MutableList<AnswerInfo> = mutableListOf()

    @ManyToOne
    @JoinColumn
    var type: EventQuestionType? = null

}