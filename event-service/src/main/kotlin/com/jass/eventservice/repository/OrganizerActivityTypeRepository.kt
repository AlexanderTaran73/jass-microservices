package com.jass.eventservice.repository

import com.jass.eventservice.module.OrganizerActivityType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizerActivityTypeRepository: JpaRepository<OrganizerActivityType, Int> {
}
