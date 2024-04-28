package com.jass.profileservice.dto

import com.jass.profileservice.module.PersonalInfo

class ClosedPersonalInfo {
    var firstName: String? = null
    var lastName: String? = null

    fun personalInfoToClosedPersonalInfo(personalInfo: PersonalInfo?): ClosedPersonalInfo?{
        if (personalInfo == null) return null
        firstName = personalInfo.firstName
        lastName = personalInfo.lastName
        return this
    }
}