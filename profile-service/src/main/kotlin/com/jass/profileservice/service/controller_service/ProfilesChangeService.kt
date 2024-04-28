package com.jass.profileservice.service.controller_service


import com.jass.profileservice.service.model_service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProfilesChangeService(
    private val profileService: ProfileService,
    private val genderService: GenderService,
    private val residenceCountryService: ResidenceCountryService,
    private val profileVisibilityService: ProfileVisibilityService,
    private val profileColorThemeService: ProfileColorThemeService,
    private val profileLanguageService: ProfileLanguageService
) {

    fun changePersonalInfo(userName: String?,
                           firstName: String?,
                           lastName: String?,
                           genderName: String?,
                           birthDate: String?,
                           residenceCountry: String?,
                           email: String): ResponseEntity<HttpStatus> {
        val profile = profileService.findByUserEmail(email) ?: return ResponseEntity( HttpStatus.NOT_FOUND)
        if (userName != null) profile.userName = userName
        profile.personal_info.also {
            if (firstName != null) it.firstName = firstName
            if (lastName != null) it.lastName = lastName
            if (genderName != null && genderService.findByName(genderName) != null) it.gender = genderService.findByName(genderName)
            if (birthDate != null) it.birthDate = birthDate
            if (residenceCountry != null && residenceCountryService.findByName(residenceCountry) != null) it.residenceCountry = residenceCountryService.findByName(residenceCountry)
        }
        profileService.save(profile)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    fun changeProfileSettings(profileVisibility: String?,
                              language: String?,
                              colorTheme: String?,
                              email: String): ResponseEntity<HttpStatus> {
        val profile = profileService.findByUserEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        profile.profile_settings.also {
            if (profileVisibility != null && profileVisibilityService.findByName(profileVisibility) != null) it.profileVisibility = profileVisibilityService.findByName(profileVisibility)
            if (language != null && profileLanguageService.findByName(language) != null) it.language = profileLanguageService.findByName(language)
            if (colorTheme != null && profileColorThemeService.findByName(colorTheme) != null) it.colorTheme = profileColorThemeService.findByName(colorTheme)
        }
        profileService.save(profile)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}