package com.jass.eventservice.service.controller_service

import com.jass.eventservice.dto.Request.EventRuleRequest
import com.jass.eventservice.module.EventRule
import com.jass.eventservice.service.module_service.EventService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class EventRulesService(
    private val eventService: EventService,
) {
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
}
