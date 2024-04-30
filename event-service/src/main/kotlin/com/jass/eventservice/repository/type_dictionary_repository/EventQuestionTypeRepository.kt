package com.jass.eventservice.repository.type_dictionary_repository

import com.jass.eventservice.module.type_dictionary.EventQuestionType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventQuestionTypeRepository: JpaRepository<EventQuestionType, Int> {

    fun findByName(name: String): EventQuestionType?
}