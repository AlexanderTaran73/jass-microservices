package com.jass.eventservice.controller

import com.jass.eventservice.service.controller_service.ParticipantsService
import org.springframework.http.HttpStatus

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/event/participants")
class ParticipantsController(
    private val participantsService: ParticipantsService
) {

    @PatchMapping("/participationRequest")
    fun participationRequest(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int): ResponseEntity<HttpStatus> {
        return participantsService.participationRequest(id, eventId)
    }

    @PatchMapping("/cancelParticipationRequest")
    fun cancelParticipationRequest(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int): ResponseEntity<HttpStatus> {
        return participantsService.cancelParticipationRequest(id, eventId)
    }

}