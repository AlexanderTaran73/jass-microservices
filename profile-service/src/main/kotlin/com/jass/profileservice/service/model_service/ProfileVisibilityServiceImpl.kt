package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ProfileVisibility
import com.jass.profileservice.repository.ProfileVisibilityRepository
import org.springframework.stereotype.Service

@Service
class ProfileVisibilityServiceImpl(
    private val profileVisibilityRepository: ProfileVisibilityRepository
): ProfileVisibilityService {
    override fun findByName(name: String): ProfileVisibility? {
        return profileVisibilityRepository.findByName(name)
    }
}