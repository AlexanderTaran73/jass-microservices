package com.jass.profileservice.dto.my_profile

import com.jass.profileservice.module.ProfileColorTheme
import com.jass.profileservice.module.ProfileLanguage
import com.jass.profileservice.module.ProfileSettings
import com.jass.profileservice.module.ProfileVisibility

class MyProfileSettings {
    var profileVisibility: ProfileVisibility? = null
    var language: ProfileLanguage? = null
    var colorTheme: ProfileColorTheme? = null
    fun profileSettingsMyProfileSettings(profileSettings: ProfileSettings?): MyProfileSettings?{
        if (profileSettings == null) return null
        profileVisibility = profileSettings.profileVisibility
        language = profileSettings.language
        colorTheme = profileSettings.colorTheme
        return this
    }
}