package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.Profile
import com.jass.profileservice.repository.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    private val profileRepository: ProfileRepository
): ProfileService {
    override fun save(profile: Profile) {
        profileRepository.save(profile)
    }

    override fun delete(profile: Profile) {
        profileRepository.delete(profile)
    }

    override fun findByUserEmail(userEmail: String): Profile? {
        return profileRepository.findByUserEmail(userEmail)
    }
}