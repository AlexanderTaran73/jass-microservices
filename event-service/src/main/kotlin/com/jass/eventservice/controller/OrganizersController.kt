package com.jass.eventservice.controller

import com.jass.eventservice.dto.EventTokenResponse
import com.jass.eventservice.dto.Request.EventOrganizerDTO
import com.jass.eventservice.service.controller_service.OrganizersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/event/organizers")
class OrganizersController(
    private val organizersService: OrganizersService
) {
    @PatchMapping("/confirmParticipation")
    fun confirmParticipation(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("participantId") participantId: Int): ResponseEntity<HttpStatus> {
        return organizersService.confirmParticipation(id, eventId, participantId)
    }

    @PostMapping("/addOrganizer")
    fun addOrganizer(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestBody organizer: EventOrganizerDTO): ResponseEntity<HttpStatus> {
        return organizersService.addOrganizer(id, eventId, organizer)
    }

    @PatchMapping("/changeOrganizer")
    fun changeOrganizer(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestBody organizer: EventOrganizerDTO): ResponseEntity<HttpStatus> {
        return organizersService.changeOrganizer(id, eventId, organizer)
    }
    @DeleteMapping("/deleteOrganizer")
    fun deleteOrganizer(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("organizerId") organizerId: Int): ResponseEntity<HttpStatus> {
        return organizersService.deleteOrganizer(id, eventId, organizerId)
    }

    @GetMapping("/getEventToken")
    fun getEventToken(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam tokenType: String): ResponseEntity<EventTokenResponse> {
        return organizersService.getEventToken(id, eventId, tokenType)
    }
}