package com.jass.profileservice.repository

import com.jass.profileservice.module.ProfileVisibility
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileVisibilityRepository: JpaRepository<ProfileVisibility, Int> {
    fun findByName(name: String): ProfileVisibility?
}