package com.jass.eventservice.module


import com.jass.eventservice.module.type_dictionary.EventType
import jakarta.persistence.*

@Entity
@Table
class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name: String? = null // max length = 100

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn
    var eventDescription: EventDescription? = null

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn
    var eventSettings: EventSettings? = null

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    var eventOrganizers: MutableList<EventOrganizer> = mutableListOf()

    @ManyToOne
    @JoinColumn
    var eventType: EventType? = null

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    var participants: MutableList<Participant> = mutableListOf()

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    var participantsRequests: MutableList<Participant> = mutableListOf()

    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    var questions: MutableList<EventQuestion> = mutableListOf()
//    TODO: add controller
    @OneToMany(cascade = [CascadeType.ALL])
    @JoinColumn
    var rules: MutableList<EventRule> = mutableListOf()
//    TODO: add controller

}