package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.Request.*
import com.jass.eventservice.feign.ImageService
import com.jass.eventservice.module.EventQuestion
import com.jass.eventservice.module.EventRule
import com.jass.eventservice.module.QuestionInfo
import com.jass.eventservice.repository.type_dictionary_repository.*
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.net.http.HttpHeaders
import java.time.LocalDateTime

@Service
class EventChangeService(
    private val eventService: EventService,
    private val eventVisibilityRepository: EventVisibilityRepository,
    private val accessToEventRepository: AccessToEventRepository,
    private val participantsVisibilityRepository: ParticipantsVisibilityRepository,
    private val eventTypeRepository: EventTypeRepository,
    private val imageService: ImageService,
    private val eventQuestionTypeRepository: EventQuestionTypeRepository
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

    fun addEventRule(id: Int, eventId: Int, rule: EventRuleRequest): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    event.rules.add(EventRule().also { it.name = rule.name; it.description = rule.description })
                    eventService.save(event)
                    return ResponseEntity(HttpStatus.CREATED)
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    fun changeEventRule(id: Int, eventId: Int, ruleId: Int, rule: EventRuleRequest): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    for (j in event.rules) {
                        if (j.id == ruleId) {
                            j.name = rule.name
                            j.description = rule.description
                            eventService.save(event)
                            return ResponseEntity(HttpStatus.CREATED)
                        }
                    }
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    fun deleteEventRule(id: Int, eventId: Int, ruleId: Int): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR"
                ) {
                    for (j in event.rules) {
                        if (j.id == ruleId) {
                            event.rules.remove(j)
                            eventService.save(event)
                            return ResponseEntity(HttpStatus.CREATED)
                        }
                    }
                }
            }
        }
        return ResponseEntity(HttpStatus.FORBIDDEN)
    }

    fun addEventQuestion(id: Int, eventId: Int, question: QuestionRequest): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            event.questions.add(
                EventQuestion().also { eventQuestion ->
                    eventQuestion.question = QuestionInfo().also {
                        it.title = question.title
                        it.text = question.text
                        it.requesterId = id
                        it.last_updated = LocalDateTime.now().toString()
                    }
                    eventQuestion.type = eventQuestionTypeRepository.findById(0).get() // CLOSED
                }
            )
            eventService.save(event)
            return ResponseEntity(HttpStatus.CREATED)
        }
    }

    fun changeEventQuestion(id: Int, eventId: Int, questionId: Int, question: QuestionRequest): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            event.questions.forEach {
                if (it.id == questionId) {
                    if (it.question!!.requesterId != id) {
                        return ResponseEntity(HttpStatus.FORBIDDEN)
                    }
                    it.question!!.title = question.title
                    it.question!!.text = question.text
                    it.question!!.last_updated = LocalDateTime.now().toString()
                    eventService.save(event)
                    return ResponseEntity(HttpStatus.NO_CONTENT)
                }
            }
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    fun deleteEventQuestion(id: Int, eventId: Int, questionId: Int): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            event.questions.forEach {
                if (it.id == questionId) {
                    if (it.question!!.requesterId != id || it.type!!.id == 1 /* OPEN */) {
                        return ResponseEntity(HttpStatus.FORBIDDEN)
                    }

                    eventService.save(event)
                    return ResponseEntity(HttpStatus.NO_CONTENT)
                }
            }
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}