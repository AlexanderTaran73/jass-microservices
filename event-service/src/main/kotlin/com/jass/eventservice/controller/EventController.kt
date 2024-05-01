package com.jass.eventservice.controller

import com.jass.eventservice.dto.EventResponse
import com.jass.eventservice.dto.Request.CreateEventRequest
import com.jass.eventservice.service.controller_service.EventsService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/event")
class EventsController(
    private val eventsService: EventsService
) {

    @PostMapping("/create")
    fun create(@RequestHeader("User-Id") id: Int, @RequestBody createEventRequest: CreateEventRequest): ResponseEntity<HttpHeaders> {
        return eventsService.create(id, createEventRequest)
    }

    @GetMapping("/get/{id}")
    fun get(@RequestHeader("User-Id") id: Int, @PathVariable("id") eventId: Int): ResponseEntity<EventResponse> {
        return eventsService.get(id, eventId)
    }
}