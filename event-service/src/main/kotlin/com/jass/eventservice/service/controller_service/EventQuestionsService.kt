package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.Request.AnswerRequest
import com.jass.eventservice.dto.Request.QuestionRequest
import com.jass.eventservice.module.AnswerInfo
import com.jass.eventservice.module.EventQuestion
import com.jass.eventservice.module.QuestionInfo
import com.jass.eventservice.repository.type_dictionary_repository.*
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class EventQuestionsService(
    private val eventService: EventService,
    private val eventQuestionTypeRepository: EventQuestionTypeRepository
) {
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
                    if (it.question!!.requesterId != id || it.type!!.id == 1 || !event.eventOrganizers.any { organizer ->
                            organizer.userId == id &&
                                    (organizer.organizerRights!!.name == "OWNER"
                                            || organizer.organizerRights!!.name == "CO-OWNER"
                                            || organizer.organizerRights!!.name == "EDITOR"
                                            || organizer.organizerRights!!.name == "HELPER")} /* OPEN */) {
                        return ResponseEntity(HttpStatus.FORBIDDEN)
                    }
                    event.questions.remove(it)
                    eventService.save(event)
                    return ResponseEntity(HttpStatus.NO_CONTENT)
                }
            }
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    fun changeEventStatus(id: Int, eventId: Int, status: String, questionId: Int): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR" ||
                    i.organizerRights!!.name == "HELPER"
                ) {
                    event.questions.forEach {
                        if (it.id == questionId) {
                            it.type = eventQuestionTypeRepository.findByName(status)!!
                            eventService.save(event)
                            return ResponseEntity(HttpStatus.NO_CONTENT)
                        }
                    }
                    return ResponseEntity(HttpStatus.NOT_FOUND)
                }
            }
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

    fun answerEventQuestion(id: Int, eventId: Int, questionId: Int, answer: AnswerRequest): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            for (i in event.eventOrganizers) {
                if (i.userId == id &&
                    i.organizerRights!!.name == "OWNER" ||
                    i.organizerRights!!.name == "CO-OWNER" ||
                    i.organizerRights!!.name == "EDITOR" ||
                    i.organizerRights!!.name == "HELPER"
                ) {
                    event.questions.forEach {
                        if (it.id == questionId) {

                            it.answers.add(AnswerInfo().also { answerInfo ->
                                answerInfo.responderId = id
                                answerInfo.text = answer.text
                                answerInfo.last_updated = LocalDateTime.now().toString()
                            })


                            eventService.save(event)
                            return ResponseEntity(HttpStatus.NO_CONTENT)
                        }
                    }
                    return ResponseEntity(HttpStatus.NOT_FOUND)
                }
            }
            return ResponseEntity(HttpStatus.FORBIDDEN)
        }
    }

    fun changeEventAnswer(id: Int, eventId: Int, questionId: Int, answerId: Int, answer: AnswerRequest): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            event.questions.forEach {
                if (it.id == questionId) {
                    it.answers.forEach {
                        if (it.id == answerId) {
                            if (it.responderId != id) {
                                return ResponseEntity(HttpStatus.FORBIDDEN)
                            }
                            it.text = answer.text
                            it.last_updated = LocalDateTime.now().toString()
                            eventService.save(event)
                            return ResponseEntity(HttpStatus.NO_CONTENT)
                        }
                    }
                }
            }
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    fun deleteEventAnswer(id: Int, eventId: Int, questionId: Int, answerId: Int): ResponseEntity<HttpStatus> {
        val event = eventService.findById(eventId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        event.also { event ->
            event.questions.forEach { question ->
                if (question.id == questionId) {
                    question.answers.forEach {
                        if (it.id == answerId) {
                            if (it.responderId != id) {
                                return ResponseEntity(HttpStatus.FORBIDDEN)
                            }
                            question.answers.remove(it)
                            eventService.save(event)
                            return ResponseEntity(HttpStatus.NO_CONTENT)
                        }
                    }
                }
            }
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
