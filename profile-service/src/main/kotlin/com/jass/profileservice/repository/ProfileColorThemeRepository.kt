package com.jass.profileservice.repository

import com.jass.profileservice.module.ProfileColorTheme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileColorThemeRepository: JpaRepository<ProfileColorTheme, Int> {

    fun findByName(name: String): ProfileColorTheme
}