package com.jass.eventservice.controller

import com.jass.eventservice.dto.CreateEventRequest
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

}