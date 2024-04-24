package com.jass.profileservice.dto

import com.jass.profileservice.module.ImageInfo
import com.jass.profileservice.module.PersonalInfo
import com.jass.profileservice.module.Profile
import com.jass.profileservice.module.ProfileSettings


class ShortProfile {
    var userEmail = ""
    var userName: String = ""
    var personal_info: ShortPersonalInfo? = null
    var images: MutableList<ImageInfo?> = mutableListOf()
    var profile_settings: ShortProfileSettings? = null

    fun profileToShortProfile(profile: Profile): ShortProfile{
        userEmail = profile.userEmail
        userName = profile.userName
        personal_info = ShortPersonalInfo().personalInfoToShortPersonalInfo(profile.personal_info)
        images = profile.images
        profile_settings = ShortProfileSettings().profileSettingsToShortProfileSettings(profile.profile_settings)
        return this
    }
}