package com.jass.eventservice.repository

import com.jass.eventservice.module.EventOrganizer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventOrganizerRepository: JpaRepository<EventOrganizer, Int>{

}