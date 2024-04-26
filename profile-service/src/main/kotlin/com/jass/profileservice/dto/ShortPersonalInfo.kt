package com.jass.profileservice.dto

import com.jass.profileservice.module.Gender
import com.jass.profileservice.module.PersonalInfo
import com.jass.profileservice.module.ResidenceCountry

class ShortPersonalInfo {
    var firstName: String? = null
    var lastName: String? = null
    var gender: Gender? = null
    var birthDate: String? = null
    var residenceCountry: ResidenceCountry? = null

    fun personalInfoToShortPersonalInfo(personalInfo: PersonalInfo?): ShortPersonalInfo?{
        if (personalInfo == null) return null
        firstName = personalInfo.firstName
        lastName = personalInfo.lastName
        gender = personalInfo.gender
        birthDate = personalInfo.birthDate
        residenceCountry = personalInfo.residenceCountry
        return this
    }
}