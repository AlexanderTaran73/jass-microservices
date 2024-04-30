package com.jass.eventservice.repository.type_dictionary_repository

import com.jass.eventservice.module.type_dictionary.EventVisibility
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventVisibilityRepository: JpaRepository<EventVisibility, Int> {

    fun findByName(name: String): EventVisibility?
}