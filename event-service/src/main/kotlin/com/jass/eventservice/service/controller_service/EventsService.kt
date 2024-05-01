package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.Request.CreateEventRequest
import com.jass.eventservice.dto.EventResponse
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@Service
class EventsService(
    private val eventService: EventService
) {
    fun create(id: Int, createEventRequest: CreateEventRequest): ResponseEntity<HttpHeaders> {
        val event = eventService.create(id, createEventRequest)
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
            .replacePath("/api/v1/event/get")
            .pathSegment("{id}")
            .buildAndExpand(event.id)
            .toUri())
            .build();
    }

    fun get(id: Int, eventId: Int): ResponseEntity<EventResponse> {
        val event = eventService.findById(eventId)
        if (event.eventSettings!!.eventVisibility!!.name == "PRIVATE" && !event.eventOrganizers.any{it.userId == id}) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity(EventResponse().eventToResponse(id, eventService.findById(eventId)), HttpStatus.OK)
    }

}