package com.jass.eventservice.repository

import com.jass.eventservice.module.EventRule
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventRuleRepository: JpaRepository<EventRule, Int> {
}