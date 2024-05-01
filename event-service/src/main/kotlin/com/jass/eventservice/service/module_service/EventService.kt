package com.jass.eventservice.service.module_service

import com.jass.eventservice.dto.Request.CreateEventRequest
import com.jass.eventservice.module.Event

interface EventService {

    fun create(id: Int, createEventRequest: CreateEventRequest): Event

    fun save(event: Event)

    fun findById(id: Int): Event

    fun findAll(): List<Event>

    fun delete(event: Event)

    fun findByEventOrganizersUserId(userId: Int): List<Event>

}