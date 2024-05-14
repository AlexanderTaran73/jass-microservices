package com.jass.eventservice.controller

import com.jass.eventservice.dto.Request.AnswerRequest
import com.jass.eventservice.dto.Request.QuestionRequest
import com.jass.eventservice.service.controller_service.EventQuestionsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/event/questions")
class EventQuestionsController(
    private val eventQuestionsService: EventQuestionsService
) {
    @PostMapping("/addEventQuestion")
    fun addEventQuestion(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestBody question: QuestionRequest): ResponseEntity<HttpStatus> {
        return eventQuestionsService.addEventQuestion(id, eventId, question)
    }

    @PatchMapping("/changeEventQuestion")
    fun changeEventQuestion(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("questionId") questionId: Int, @RequestBody question: QuestionRequest): ResponseEntity<HttpStatus> {
        return eventQuestionsService.changeEventQuestion(id, eventId, questionId, question)
    }

    @DeleteMapping("/deleteEventQuestion")
    fun deleteEventQuestion(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("questionId") questionId: Int): ResponseEntity<HttpStatus> {
        return eventQuestionsService.deleteEventQuestion(id, eventId, questionId)
    }

    @PatchMapping("/changeEventStatus")
    fun changeEventStatus(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("status") status: String, @RequestParam("questionId") questionId: Int): ResponseEntity<HttpStatus> {
        return eventQuestionsService.changeEventStatus(id, eventId, status, questionId)
    }

    @PostMapping("/answerEventQuestion")
    fun answerEventQuestion(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("questionId") questionId: Int, @RequestBody answer: AnswerRequest): ResponseEntity<HttpStatus> {
        return eventQuestionsService.answerEventQuestion(id, eventId, questionId, answer)
    }

    @PatchMapping("/changeEventAnswer")
    fun changeEventAnswer(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("questionId") questionId: Int, @RequestParam("answerId") answerId: Int, @RequestBody answer: AnswerRequest): ResponseEntity<HttpStatus> {
        return eventQuestionsService.changeEventAnswer(id, eventId, questionId, answerId, answer)
    }

    @DeleteMapping("/deleteEventAnswer")
    fun deleteEventAnswer(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("questionId") questionId: Int, @RequestParam("answerId") answerId: Int): ResponseEntity<HttpStatus> {
        return eventQuestionsService.deleteEventAnswer(id, eventId, questionId, answerId)
    }

}