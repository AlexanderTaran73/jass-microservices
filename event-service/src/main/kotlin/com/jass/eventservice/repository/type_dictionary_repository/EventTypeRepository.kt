package com.jass.eventservice.repository.type_dictionary_repository

import com.jass.eventservice.module.type_dictionary.EventType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventTypeRepository: JpaRepository<EventType, Int> {

    fun findByName(name: String): EventType?
}