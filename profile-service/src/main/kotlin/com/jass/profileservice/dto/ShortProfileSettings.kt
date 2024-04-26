package com.jass.profileservice.dto

import com.jass.profileservice.module.ProfileColorTheme
import com.jass.profileservice.module.ProfileLanguage
import com.jass.profileservice.module.ProfileSettings
import com.jass.profileservice.module.ProfileVisibility

class ShortProfileSettings {
    var profileVisibility: ProfileVisibility? = null
    var language: ProfileLanguage? = null
    var colorTheme: ProfileColorTheme? = null
    fun profileSettingsToShortProfileSettings(profileSettings: ProfileSettings?): ShortProfileSettings?{
        if (profileSettings == null) return null
        profileVisibility = profileSettings.profileVisibility
        language = profileSettings.language
        colorTheme = profileSettings.colorTheme
        return this
    }
}