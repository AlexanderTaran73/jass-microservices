package com.jass.eventservice.repository

import com.jass.eventservice.module.EventSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventSettingsRepository: JpaRepository<EventSettings, Int> {
}