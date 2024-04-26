package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ProfileLanguage
import com.jass.profileservice.repository.ProfileLanguageRepository
import org.springframework.stereotype.Service

@Service
class ProfileLanguageServiceImpl(
    private val profileLanguageRepository: ProfileLanguageRepository
): ProfileLanguageService {

    override fun findByName(name: String): ProfileLanguage? {
        return profileLanguageRepository.findByName(name)
    }
}