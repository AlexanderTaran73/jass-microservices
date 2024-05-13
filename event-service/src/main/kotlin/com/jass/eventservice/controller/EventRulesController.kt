package com.jass.eventservice.controller

import com.jass.eventservice.dto.Request.EventRuleRequest
import com.jass.eventservice.service.controller_service.EventRulesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/event/rules")
class EventRulesController(
    private val eventRulesService: EventRulesService
) {

    @PostMapping("/addEventRule")
    fun addEventRule(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestBody rule: EventRuleRequest): ResponseEntity<HttpStatus> {
        return eventRulesService.addEventRule(id, eventId, rule)
    }

    @PatchMapping("/changeEventRule")
    fun changeEventRule(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("ruleId") ruleId: Int, @RequestBody rule: EventRuleRequest): ResponseEntity<HttpStatus> {
        return eventRulesService.changeEventRule(id, eventId,ruleId, rule)
    }

    @DeleteMapping("/deleteEventRule")
    fun deleteEventRule(@RequestHeader("User-Id") id: Int, @RequestParam("eventId") eventId: Int, @RequestParam("ruleId") ruleId: Int): ResponseEntity<HttpStatus> {
        return eventRulesService.deleteEventRule(id, eventId, ruleId)
    }
}