package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ProfileColorTheme
import com.jass.profileservice.repository.ProfileColorThemeRepository
import org.springframework.stereotype.Service

@Service
class ProfileColorThemeServiceImpl(
    private val profileColorThemeRepository: ProfileColorThemeRepository
): ProfileColorThemeService {

    override fun findByName(name: String): ProfileColorTheme? {
        return profileColorThemeRepository.findByName(name)
    }
}