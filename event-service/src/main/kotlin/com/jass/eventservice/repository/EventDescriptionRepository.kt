package com.jass.eventservice.repository

import com.jass.eventservice.module.EventDescription
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventDescriptionRepository: JpaRepository<EventDescription, Int> {
}