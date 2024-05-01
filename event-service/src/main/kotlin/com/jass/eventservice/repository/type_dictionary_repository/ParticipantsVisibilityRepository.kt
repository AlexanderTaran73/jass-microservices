package com.jass.eventservice.repository.type_dictionary_repository

import com.jass.eventservice.module.type_dictionary.ParticipantsVisibility
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParticipantsVisibilityRepository: JpaRepository<ParticipantsVisibility, Int> {

    fun findByName(name: String): ParticipantsVisibility?
}