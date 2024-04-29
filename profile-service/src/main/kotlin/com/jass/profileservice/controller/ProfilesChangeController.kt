package com.jass.profileservice.controller


import com.jass.profileservice.service.controller_service.ProfilesChangeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

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
                           @RequestHeader("User-Email") email: String): ResponseEntity<HttpStatus> {
        return profilesChangeService.changePersonalInfo(userName, firstName, lastName, gender_name, birth_date, residenceCountry, email)
    }

    @PatchMapping("/change_profile_settings")
    fun changeProfileSettings(@RequestParam profileVisibility: String?,
                               @RequestParam language: String?,
                               @RequestParam colorTheme: String?,
                               @RequestHeader("User-Email") email: String): ResponseEntity<HttpStatus> {
        return profilesChangeService.changeProfileSettings(profileVisibility, language, colorTheme, email)
    }

    @PatchMapping("/change_avatar_image")
    fun changeAvatarImage(@RequestHeader("User-Email") email: String, @RequestParam("imageFile") imageFile: MultipartFile): ResponseEntity<HttpStatus> {
        return profilesChangeService.changeAvatarImage(email, imageFile)
    }

    @PatchMapping("/add_profile_image")
    fun addProfileImage(@RequestHeader("User-Email") email: String, @RequestParam("imageFile") imageFile: MultipartFile): ResponseEntity<HttpStatus> {
        return profilesChangeService.addProfileImage(email, imageFile)
    }

    @DeleteMapping("/delete_profile_image")
    fun deleteProfileImage(@RequestHeader("User-Email") email: String, @RequestParam fileName: String): ResponseEntity<HttpStatus> {
        return profilesChangeService.deleteProfileImage(email, fileName)
    }
}