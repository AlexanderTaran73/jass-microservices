package com.jass.eventservice.controller

import com.jass.eventservice.dto.Request.EventDescriptionDTO
import com.jass.eventservice.service.controller_service.EventChangeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.http.HttpHeaders

@RestController
@RequestMapping("/api/v1/event/change")
class EventChangeController(
    private val eventChangeService: EventChangeService
) {


    @PatchMapping("/changEventDescription")
    fun changEventDescription(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestBody description: EventDescriptionDTO): ResponseEntity<HttpStatus> {
        return eventChangeService.changEventDescription(id, eventId, description)
    }

}