package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.Request.*
import com.jass.eventservice.feign.ImageService
import com.jass.eventservice.repository.type_dictionary_repository.AccessToEventRepository
import com.jass.eventservice.repository.type_dictionary_repository.EventTypeRepository
import com.jass.eventservice.repository.type_dictionary_repository.EventVisibilityRepository
import com.jass.eventservice.repository.type_dictionary_repository.ParticipantsVisibilityRepository
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.net.http.HttpHeaders

@Service
class EventChangeService(
    private val eventService: EventService,
    private val eventVisibilityRepository: EventVisibilityRepository,
    private val accessToEventRepository: AccessToEventRepository,
    private val participantsVisibilityRepository: ParticipantsVisibilityRepository,
    private val eventTypeRepository: EventTypeRepository,
    private val imageService: ImageService
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
                    eventService.save(event)
                    return ResponseEntity(HttpStatus.CREATED)
                }
            }
        }
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

    fun changEventName(id: Int, eventId: Int, name: String): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    event.name = name
                    eventService.save(event)
                    return ResponseEntity(HttpStatus.CREATED)
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    fun changEventSettings(id: Int, eventId: Int, settings: EventSettingsDTO): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    event.eventSettings.also {
                        if (eventVisibilityRepository.findByName(settings.eventVisibility) != null) {
                            it!!.eventVisibility = eventVisibilityRepository.findByName(settings.eventVisibility)
                        }
                        if (accessToEventRepository.findByName(settings.accessToEvent) != null) {
                            it!!.accessToEvent = accessToEventRepository.findByName(settings.accessToEvent)
                        }
                        if (participantsVisibilityRepository.findByName(settings.participantsVisibility) != null) {
                            it!!.participantsVisibility = participantsVisibilityRepository.findByName(settings.participantsVisibility)
                        }
                        eventService.save(event)
                        return ResponseEntity(HttpStatus.CREATED)
                    }
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    fun changEventType(id: Int, eventId: Int, type: String): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    if (eventTypeRepository.findByName(type) != null) {
                        event.eventType = eventTypeRepository.findByName(type)
                    }
                    eventService.save(event)
                    return ResponseEntity(HttpStatus.CREATED)
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    fun addEventImage(id: Int, eventId: Int, imageFile: MultipartFile): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    imageService.saveImage(imageFile, "EventImage", event.id)
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    fun deleteEventImage(id: Int, eventId: Int, fileName: String): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    imageService.deleteImage(fileName)
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }
}