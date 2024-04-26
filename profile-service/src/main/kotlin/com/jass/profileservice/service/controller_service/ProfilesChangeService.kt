package com.jass.profileservice.service.controller_service

import com.jass.profileservice.dto.ShortProfile
import com.jass.profileservice.service.model_service.GenderService
import com.jass.profileservice.service.model_service.ProfileService
import com.jass.profileservice.service.model_service.ResidenceCountryService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ProfilesChangeService(
    private val profileService: ProfileService,
    private val genderService: GenderService,
    private val residenceCountryService: ResidenceCountryService
) {

    fun changePersonalInfo(userName: String?,
                           firstName: String?,
                           lastName: String?,
                           genderName: String?,
                           birthDate: String?,
                           residenceCountry: String?,
                           email: String): ResponseEntity<Any> {
        val profile = profileService.findByUserEmail(email) ?: return ResponseEntity.notFound().build()
        if (userName != null) profile.userName = userName
        profile.personal_info.also {
            if (firstName != null) it.firstName = firstName
            if (lastName != null) it.lastName = lastName
            if (genderName != null && genderService.findByName(genderName) != null) it.gender = genderService.findByName(genderName)
            if (birthDate != null) it.birthDate = birthDate
            if (residenceCountry != null && residenceCountryService.findByName(residenceCountry) != null) it.residenceCountry = residenceCountryService.findByName(residenceCountry)
        }
        profileService.save(profile)

        return ResponseEntity(ShortProfile().profileToShortProfile(profile), HttpStatus.OK)
    }

}