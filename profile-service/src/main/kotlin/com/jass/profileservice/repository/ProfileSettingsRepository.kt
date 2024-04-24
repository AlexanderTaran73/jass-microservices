package com.jass.profileservice.repository

import com.jass.profileservice.module.ProfileSettings
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProfileSettingsRepository: JpaRepository<ProfileSettings, Int> {
}