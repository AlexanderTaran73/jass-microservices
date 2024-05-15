package com.jass.eventservice.repository

import com.jass.eventservice.module.Event
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
@Transactional
interface EventRepository: JpaRepository<Event, Int> {
    fun findByEventOrganizersUserId(userId: Int): List<Event>

    fun findAllByParticipantsUserId(userId: Int): List<Event>

    fun deleteByIdNotIn(id: List<Int>)
}