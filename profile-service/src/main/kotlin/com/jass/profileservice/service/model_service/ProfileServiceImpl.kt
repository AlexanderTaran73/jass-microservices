package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.PersonalInfo
import com.jass.profileservice.module.Profile
import com.jass.profileservice.module.ProfileSettings
import com.jass.profileservice.repository.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    private val profileRepository: ProfileRepository,
    private val genderService: GenderService,
    private val profileColorThemeService: ProfileColorThemeService,
    private val profileLanguageService: ProfileLanguageService,
    private val profileVisibilityService: ProfileVisibilityService,
    private val residenceCountryService: ResidenceCountryService,
): ProfileService {

    override fun create(email: String, userId: Int): Profile {
        return Profile().also { profile ->
            profile.id = userId
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
            save(profile)
        }

    }

    override fun save(profile: Profile) {
        profileRepository.save(profile)
    }

    override fun delete(profile: Profile) {
        profileRepository.delete(profile)
    }

    override fun findAll(): List<Profile> {
        return profileRepository.findAll()
    }

    override fun findByUserEmail(userEmail: String): Profile? {
        return profileRepository.findByUserEmail(userEmail)
    }

    override fun findById(id: Int): Profile? {
        return profileRepository.findById(id).get()
    }
}