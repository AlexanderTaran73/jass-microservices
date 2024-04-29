package com.jass.profileservice.dto.short_profile

import com.jass.profileservice.module.PersonalInfo

class ShortClosedPersonalInfo {
    var firstName: String? = null
    var lastName: String? = null

    fun personalInfoToShortClosedPersonalInfo(personalInfo: PersonalInfo?): ShortClosedPersonalInfo?{
        if (personalInfo == null) return null
        firstName = personalInfo.firstName
        lastName = personalInfo.lastName
        return this
    }
}