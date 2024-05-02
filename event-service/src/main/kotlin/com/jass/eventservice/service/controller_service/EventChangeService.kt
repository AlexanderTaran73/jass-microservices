package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.Request.EventDescriptionDTO
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.net.http.HttpHeaders

@Service
class EventChangeService(
    private val eventService: EventService
) {
    fun changEventDescription(id: Int, eventId: Int, description: EventDescriptionDTO): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    event.eventDescription.also {
                        it!!.description_text = description.description_text
                        it.data = description.data
                        it.time = description.time
                        it.location = description.location
                        it.participants_count = description.participants_count
                    }
                }
            }
        }
        eventService.save(event)
        return ResponseEntity(HttpStatus.CREATED)
    }
}