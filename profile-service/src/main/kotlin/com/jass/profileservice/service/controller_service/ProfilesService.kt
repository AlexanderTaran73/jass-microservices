package com.jass.profileservice.service.controller_service

import com.jass.profileservice.dto.ShortProfile
import com.jass.profileservice.module.PersonalInfo
import com.jass.profileservice.module.Profile
import com.jass.profileservice.module.ProfileSettings
import com.jass.profileservice.service.model_service.GenderService
import com.jass.profileservice.service.model_service.ImageTypeService
import com.jass.profileservice.service.model_service.ProfileService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProfilesService(
    private val profileService: ProfileService,
    private val genderService: GenderService,
    private val imageTypeService: ImageTypeService
) {
    fun createProfile(email: String): ResponseEntity<Any> {
        val profile = profileService.findByUserEmail(email) ?: Profile().also { profile ->
            profile.userEmail = email
            profile.userName = email.split("@")[0]
            profile.personal_info = PersonalInfo().also { personalInfo ->
//                TODO: add test for gender
                personalInfo.gender = genderService.findById(0)!!
            }
            profile.profile_settings = ProfileSettings().also { profileSettings ->
//                TODO: add profile settings initialization
            }
        }

        profileService.save(profile)
        return ResponseEntity(ShortProfile().profileToShortProfile(profile),HttpStatus.CREATED)
    }

    fun getProfile(email: String): ResponseEntity<Any> {
        return ResponseEntity(profileService.findByUserEmail(email) ,HttpStatus.OK)
    }
}