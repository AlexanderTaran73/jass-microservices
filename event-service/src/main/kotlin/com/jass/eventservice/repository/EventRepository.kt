package com.jass.eventservice.repository

import com.jass.eventservice.module.Event
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRepository: JpaRepository<Event, Int> {
    @Transactional
    fun findByEventOrganizersUserId(userId: Int): List<Event>
}