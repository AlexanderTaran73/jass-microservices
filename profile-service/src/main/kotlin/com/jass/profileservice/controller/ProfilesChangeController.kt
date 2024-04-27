package com.jass.profileservice.controller

import com.jass.profileservice.dto.ShortProfile
import com.jass.profileservice.service.controller_service.ProfilesChangeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/profile/change")
class ProfilesChangeController(
    private val profilesChangeService: ProfilesChangeService
) {

    @PatchMapping("/change_personal_info")
    fun changeLoginAndPersonalInfo(@RequestParam userName: String?, // TODO: may be userName not here
                           @RequestParam firstName: String?,
                           @RequestParam lastName: String?,
                           @RequestParam gender_name: String?,
                           @RequestParam birth_date: String?,
                           @RequestParam residenceCountry: String?,
                           @RequestHeader("User-Email") email: String): ResponseEntity<ShortProfile?> {
        return profilesChangeService.changePersonalInfo(userName, firstName, lastName, gender_name, birth_date, residenceCountry, email)
    }

    @PatchMapping("/change_profile_settings")
    fun changeProfileSettings(@RequestParam profileVisibility: String?,
                               @RequestParam language: String?,
                               @RequestParam colorTheme: String?,
                               @RequestHeader("User-Email") email: String): ResponseEntity<ShortProfile?> {
        return profilesChangeService.changeProfileSettings(profileVisibility, language, colorTheme, email)
    }
}