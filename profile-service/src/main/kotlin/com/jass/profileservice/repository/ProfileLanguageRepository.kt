package com.jass.profileservice.repository

import com.jass.profileservice.module.ProfileLanguage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileLanguageRepository: JpaRepository<ProfileLanguage, Int> {
    fun findByName(name: String): ProfileLanguage?
}