package com.jass.eventservice.repository

import com.jass.eventservice.module.OrganizerContacts
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrganizerContactsRepository: JpaRepository<OrganizerContacts, Int>