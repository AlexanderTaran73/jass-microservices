package com.jass.profileservice.service.controller_service

import com.jass.profileservice.dto.full_profile.FullPersonalInfo
import com.jass.profileservice.dto.full_profile.FullProfileResponse
import com.jass.profileservice.dto.my_profile.MyProfile
import com.jass.profileservice.dto.short_profile.ShortProfileResponse
import com.jass.profileservice.feign.ImageService
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
    private val residenceCountryService: ResidenceCountryService,
    private val friendInviteService: FriendInviteService,
    private val imageService: ImageService
) {
    fun createProfile(email: String, userId: Int): ResponseEntity<Any> {
        val profile = profileService.findByUserEmail(email) ?: Profile().also { profile ->
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
        }

        profileService.save(profile)
        return ResponseEntity(MyProfile(friendInviteService, imageService).profileToMyProfileResponse(profile),HttpStatus.CREATED)
    }


    fun getProfile(email: String): ResponseEntity<Any> {
        return ResponseEntity(MyProfile(friendInviteService, imageService).profileToMyProfileResponse(profileService.findByUserEmail(email)!!) ,HttpStatus.OK)
    }

    fun getAllProfiles(email: String): ResponseEntity<MutableList<ShortProfileResponse>> {
        return ResponseEntity(
            mutableListOf<ShortProfileResponse>().also { list ->
            profileService.findAll().forEach { profile ->

                var avatar: String?
                try {
                    avatar = imageService.getImageInfo("ProfileAvatar", profile.id).body!!.get(0).fileName
                } catch (e: Exception) {
                    avatar = null
                }


                if (profile.userEmail != email) list.add(ShortProfileResponse().profileToShortProfileResponse(profile, email, avatar))
            }
        }, HttpStatus.OK)
    }

    fun getProfilesByIds(email: String, id: List<Int>): ResponseEntity<MutableList<ShortProfileResponse>> {
        return ResponseEntity(
            mutableListOf<ShortProfileResponse>().also { list ->
                id.forEach { profileId ->
                    profileService.findById(profileId).let { profile ->
                        var avatar: String?
                        try {
                            avatar = imageService.getImageInfo("ProfileAvatar", profile!!.id).body!!.get(0).fileName
                        } catch (e: Exception) {
                            avatar = null
                        }

                        if (profile != null) list.add(ShortProfileResponse().profileToShortProfileResponse(profile, email, avatar))
                    }
                }
            }, HttpStatus.OK)
    }

    fun getProfileById(email: String, id: Int): ResponseEntity<FullProfileResponse?> {
        val requester = profileService.findByUserEmail(email) ?: return ResponseEntity(null, HttpStatus.BAD_REQUEST)
        return ResponseEntity(
            FullProfileResponse().also { fullProfile ->
                profileService.findById(id)?.let { profile ->
                    fullProfile.id = profile.id
                    fullProfile.userName = profile.userName
                    fullProfile.profile_visibility = profile.profile_settings.profileVisibility!!.name

                    if (fullProfile.profile_visibility == "PUBLIC" ||
                        (fullProfile.profile_visibility == "FRIENDS_ONLY" && requester.friends.any { friend -> friend.id == id })) {
                        fullProfile.personal_info = FullPersonalInfo().also { info ->
                            info.first_name = profile.personal_info.firstName
                            info.last_name = profile.personal_info.lastName
                            info.gender = profile.personal_info.gender!!.name
                            info.residence_country = profile.personal_info.residenceCountry!!.name
                        }
//                  images
                        fullProfile.images = mutableListOf<String>()
                        try {
                            val images = imageService.getImageInfo("ProfileImage", profile.id).body!!

                            images.forEach { image ->
                                fullProfile.images!!.add(image.fileName)
                            }
                        } catch (e: Exception) { }

                        fullProfile.friends_ids = requester.friends.map { it.id }.toMutableList()
                    }
                    else {
                        fullProfile.personal_info = FullPersonalInfo().also { info ->
                            info.first_name = profile.personal_info.firstName
                            info.last_name = profile.personal_info.lastName
                            info.gender = null
                            info.residence_country = null
                        }
                    }

//                  avatar
                    try {
                        fullProfile.avatar = imageService.getImageInfo("ProfileAvatar", profile.id).body!!.get(0).fileName
                    } catch (e: Exception) {
                        fullProfile.avatar = null
                    }



//                  friends_status
                    val friendsInfo = friendInviteService.findByInviterIdAndInvitedId(requester.id, id) ?:
                    friendInviteService.findByInviterIdAndInvitedId(id, requester.id)
                    if (friendsInfo == null) fullProfile.friends_status = "NOT_FRIEND"
                    else if (friendsInfo.status!!.name == "SENT") {
                        if (friendsInfo.inviterId == requester.id) fullProfile.friends_status = "FRIEND_REQUEST_SENT"
                        else fullProfile.friends_status = "FRIEND_REQUEST_RECEIVED"
                    }
                    else if (friendsInfo.status!!.name == "ACCEPTED") fullProfile.friends_status = "FRIENDS"
                    else if (friendsInfo.status!!.name == "REJECTED") {
                        if (friendsInfo.inviterId == requester.id) fullProfile.friends_status = "YOUR_REQUEST_REJECTED"
                        else fullProfile.friends_status = "YOU_REJECTED_REQUEST"
                    }
                    else fullProfile.friends_status = "NOT_FRIEND"

                }
            }, HttpStatus.OK
        )


    }


}
