package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.Request.CreateEventRequest
import com.jass.eventservice.dto.EventResponse
import com.jass.eventservice.dto.EventTokenType
import com.jass.eventservice.feign.ImageService
import com.jass.eventservice.module.Participant
import com.jass.eventservice.service.module_service.EventService
import com.jass.eventservice.utils.JwtProvider
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

//TODO: add text to all status code
@Service
class EventsService(
    private val eventService: EventService,
    private val imageService: ImageService,
    private val jwtProvider: JwtProvider,
    private val participantsService: ParticipantsService
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
        val event = eventService.findById(eventId) ?: return ResponseEntity(null, HttpStatus.NOT_FOUND)
        if (event.eventSettings!!.eventVisibility!!.name == "PRIVATE" && !event.eventOrganizers.any{it.userId == id}) {
            return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        }
        return ResponseEntity(EventResponse(imageService).eventToResponse(id, eventService.findById(eventId)!!), HttpStatus.OK)
    }

    fun getAll(id: Int): ResponseEntity<MutableList<EventResponse>> {
        return ResponseEntity(
            mutableListOf<EventResponse>().apply {
                eventService.findAll().forEach { event ->
                    if (event.eventSettings!!.eventVisibility!!.name == "PRIVATE" && !event.eventOrganizers.any{it.userId == id})
                    else add(EventResponse(imageService).eventToResponse(id, event))
                }
             }
            , HttpStatus.OK)
    }

    fun getAllByOrganizer(id: Int, organizerId: Int): ResponseEntity<MutableList<EventResponse>> {
        return ResponseEntity(
            mutableListOf<EventResponse>().apply {
                eventService.findByEventOrganizersUserId(organizerId).forEach { event ->
                    add(EventResponse(imageService).eventToResponse(id, event))
                }
            }
            , HttpStatus.OK)
    }

    fun getAllByParticipant(id: Int, participantId: Int): ResponseEntity<MutableList<EventResponse>> {
        return ResponseEntity(
            mutableListOf<EventResponse>().apply {
                eventService.findAllByParticipantsUserId(participantId).forEach { event ->
                    add(EventResponse(imageService).eventToResponse(id, event))
                }
            }
            , HttpStatus.OK)
    }

    fun applyEventToken(id: Int, eventToken: String): ResponseEntity<Any> {
        try {
            val claims = jwtProvider.decodeEventToken(eventToken)
            val event = eventService.findById(claims.body["subject"] as Int) ?: return ResponseEntity(HttpStatus.BAD_REQUEST)
            when (claims.body["tokenType"]) {
                EventTokenType.VIEW_ONLY.name -> {
                    return ResponseEntity(EventResponse(imageService).eventToResponse(id, event), HttpStatus.OK)
                }
                EventTokenType.INVITATION_FROM_PARTICIPANT.name-> {
                    return ResponseEntity(EventResponse(imageService).eventToResponse(id, event), participantsService.participationRequest(id, event.id).statusCode)
                }
                EventTokenType.INVITATION_FROM_ORGANIZER.name -> {
                    event.participants.add(Participant().also { it.userId = id })
                    return ResponseEntity(EventResponse(imageService).eventToResponse(id, event), HttpStatus.CREATED)
                }
            }
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        } catch (e: Exception) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

}