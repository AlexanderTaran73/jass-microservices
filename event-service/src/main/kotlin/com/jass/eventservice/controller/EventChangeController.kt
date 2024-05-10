package com.jass.eventservice.controller

import com.jass.eventservice.dto.Request.EventDescriptionDTO
import com.jass.eventservice.dto.Request.EventRuleRequest
import com.jass.eventservice.dto.Request.EventSettingsDTO
import com.jass.eventservice.dto.Request.QuestionRequest
import com.jass.eventservice.service.controller_service.EventChangeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
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

    @PatchMapping("/changEventName")
    fun changEventName(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam name: String): ResponseEntity<HttpStatus> {
        return eventChangeService.changEventName(id, eventId, name)
    }

    @PatchMapping("/changEventSettings")
    fun changEventSettings(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestBody settings: EventSettingsDTO): ResponseEntity<HttpStatus> {
        return eventChangeService.changEventSettings(id, eventId, settings)
    }

    @PatchMapping("/changEventType")
    fun changEventType(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam type: String): ResponseEntity<HttpStatus> {
        return eventChangeService.changEventType(id, eventId, type)
    }

//    EventImage
    @PostMapping("/addEventImage")
    fun addEventImage(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int,  @RequestParam("imageFile") imageFile: MultipartFile): ResponseEntity<HttpStatus> {
        return eventChangeService.addEventImage(id, eventId, imageFile)
    }

    @DeleteMapping("/deleteEventImage")
    fun deleteEventImage(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int,   @RequestParam fileName: String): ResponseEntity<HttpStatus> {
        return eventChangeService.deleteEventImage(id, eventId, fileName)
    }

//    EventRule
    @PostMapping("/addEventRule")
    fun addEventRule(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestBody rule: EventRuleRequest): ResponseEntity<HttpStatus> {
        return eventChangeService.addEventRule(id, eventId, rule)
    }

    @PatchMapping("/changeEventRule")
    fun changeEventRule(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int,@RequestParam("ruleId") ruleId: Int, @RequestBody rule: EventRuleRequest): ResponseEntity<HttpStatus> {
        return eventChangeService.changeEventRule(id, eventId,ruleId, rule)
    }

    @DeleteMapping("/deleteEventRule")
    fun deleteEventRule(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("ruleId") ruleId: Int): ResponseEntity<HttpStatus> {
        return eventChangeService.deleteEventRule(id, eventId, ruleId)
    }

//    EventQuestion
    @PostMapping("/addEventQuestion")
    fun addEventQuestion(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestBody question: QuestionRequest): ResponseEntity<HttpStatus> {
        return eventChangeService.addEventQuestion(id, eventId, question)
    }

    @PatchMapping("/changeEventQuestion")
    fun changeEventQuestion(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("questionId") questionId: Int, @RequestBody question: QuestionRequest): ResponseEntity<HttpStatus> {
        return eventChangeService.changeEventQuestion(id, eventId, questionId, question)
    }

    @DeleteMapping("/deleteEventQuestion")
    fun deleteEventQuestion(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("questionId") questionId: Int): ResponseEntity<HttpStatus> {
        return eventChangeService.deleteEventQuestion(id, eventId, questionId)
    }

}