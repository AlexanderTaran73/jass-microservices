package com.jass.eventservice.service.controller_service

import com.jass.eventservice.feign.UserService
import com.jass.eventservice.module.Participant
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ParticipantsService(
    private val eventService: EventService,
    private val userService: UserService
) {

    fun participationRequest(id: Int, eventId: Int): ResponseEntity<HttpStatus> {
        if (userService.getUsersShortById(listOf(id)).body!![0] == null) return ResponseEntity(HttpStatus.BAD_REQUEST)
        val event = eventService.findById(eventId)!!.also { event ->
            event.participants.forEach { participant ->
                if (participant.id == id) return ResponseEntity(HttpStatus.BAD_REQUEST)
            }
            event.participantsRequests.forEach {
                if (it.id == id) return ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }
        if (event.eventSettings!!.accessToEvent!!.name == "Open") {
            event.participants.add(Participant().also { it.userId = id })
        }
        else if (event.eventSettings!!.accessToEvent!!.name == "ByRequest"){
            event.participantsRequests.add(Participant().also { it.userId = id })
        }
        else if (event.eventSettings!!.accessToEvent!!.name == "ByPayment"){
//            TODO: add payment system
        }
        eventService.save(event)
        return ResponseEntity(HttpStatus.CREATED)
    }

    fun cancelParticipationRequest(id: Int, eventId: Int): ResponseEntity<HttpStatus> {
        eventService.findById(eventId)?.also { event ->
            event.participants.forEach { participant ->
                if (participant.id == id) {
                    event.participants.remove(participant)
                }
            }
            event.participantsRequests.forEach { participant ->
                if (participant.id == id) {
                    event.participantsRequests.remove(participant)
                }
            }
            eventService.save(event)
            return ResponseEntity(HttpStatus.CREATED)
        }
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

}