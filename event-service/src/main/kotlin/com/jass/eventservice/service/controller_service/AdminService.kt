package com.jass.eventservice.service.controller_service

import com.jass.eventservice.service.module_service.EventService
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val eventService: EventService
) {

    fun deleteEvent(id: List<Int>) {
        id.forEach { eventService.deleteById(it) }
    }

    fun deleteAllExceptId(id: List<Int>) {
        eventService.deleteByIdNotIn(id)
    }
}
