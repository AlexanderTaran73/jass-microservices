package com.jass.eventservice.dto

import com.jass.eventservice.module.*
import com.jass.eventservice.module.type_dictionary.EventType
import jakarta.persistence.*

class EventResponse {

    var id: Int = 0

    var name: String? = null

    var eventDescription: EventDescription? = null

    var eventSettings: EventSettings? = null

    var eventOrganizers: MutableList<EventOrganizer> = mutableListOf()

    var eventType: EventType? = null

    var participants: MutableList<Participant>? = null

    var questions: MutableList<EventQuestion> = mutableListOf()

    var rules: MutableList<EventRule> = mutableListOf()

    var possibilityOfEditing: String? = null

//    TODO: add images
    fun eventToResponse(requesterId: Int, event: Event): EventResponse {
        this.id = event.id
        this.name = event.name
        this.eventDescription = event.eventDescription
        this.eventSettings = event.eventSettings
        this.eventOrganizers = event.eventOrganizers
        this.eventType = event.eventType

        if (eventSettings!!.participantsVisibility!!.name == "VISIBLE") this.participants = event.participants

        this.questions = event.questions
        this.rules = event.rules

        event.eventOrganizers.forEach { organizer ->
            if (organizer.userId == requesterId) {
                this.possibilityOfEditing = organizer.organizerRights!!.name
                this.participants = event.participants

            }
        }
        if (possibilityOfEditing == null) this.possibilityOfEditing = "NONE"
        return this
    }
}