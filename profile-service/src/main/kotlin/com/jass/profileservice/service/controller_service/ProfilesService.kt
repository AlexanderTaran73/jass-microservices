package com.jass.profileservice.service.controller_service

import com.jass.profileservice.dto.*
import com.jass.profileservice.module.PersonalInfo
import com.jass.profileservice.module.Profile
import com.jass.profileservice.module.ProfileSettings
import com.jass.profileservice.service.model_service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProfilesService(
    private val profileService: ProfileService,
    private val genderService: GenderService,
    private val profileColorThemeService: ProfileColorThemeService,
    private val profileLanguageService: ProfileLanguageService,
    private val profileVisibilityService: ProfileVisibilityService,
    private val residenceCountryService: ResidenceCountryService
) {
    fun createProfile(email: String): ResponseEntity<Any> {
        val profile = profileService.findByUserEmail(email) ?: Profile().also { profile ->
            profile.userEmail = email
            profile.userName = email.split("@")[0]
            profile.personal_info = PersonalInfo().also { personalInfo ->
                personalInfo.gender = genderService.findByName("UNDEFINED")
                personalInfo.residenceCountry = residenceCountryService.findByName("UNDEFINED")
            }
            profile.profile_settings = ProfileSettings().also { profileSettings ->
                profileSettings.profileVisibility = profileVisibilityService.findByName("PUBLIC")
                profileSettings.language = profileLanguageService.findByName("ENGLISH")
                profileSettings.colorTheme = profileColorThemeService.findByName("LIGHT")
            }
        }

        profileService.save(profile)
        return ResponseEntity(MyProfile().profileToMyProfileResponse(profile),HttpStatus.CREATED)
    }


    fun getProfile(email: String): ResponseEntity<Any> {
        return ResponseEntity(MyProfile().profileToMyProfileResponse(profileService.findByUserEmail(email)!!) ,HttpStatus.OK)
    }

    fun getAllProfiles(email: String): ResponseEntity<Any> {
        return ResponseEntity(
            mutableListOf<ProfileResponse>().also { list ->
            profileService.findAll().forEach { profile ->
                if (profile.userEmail != email) list.add(ProfileResponse().profileToProfileResponse(profile, email))
            }
        }, HttpStatus.OK)
    }


}
