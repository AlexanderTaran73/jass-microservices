package com.jass.eventservice.repository.type_dictionary_repository

import com.jass.eventservice.module.type_dictionary.OrganizerRights
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizerRightsRepository: JpaRepository<OrganizerRights, Int> {

    fun findByName(name: String): OrganizerRights?
}