package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.CreateEventRequest
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpHeaders
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
}