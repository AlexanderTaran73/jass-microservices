package com.jass.profileservice.dto.short_profile

import com.jass.profileservice.dto.ImageInfoResponse
import com.jass.profileservice.module.Profile

class ShortClosedProfile: ShortProfileResponse() {
    override var id: Int = 0
    override var userName: String = ""
    override var avatar: String? = null
    var personalInfo: ShortClosedPersonalInfo? = null

    fun profileToShortClosedProfile(profile: Profile?, avatarName: String?): ShortClosedProfile?{
        if (profile == null) return null
        id = profile.id
        userName = profile.userName
        avatar = avatarName
        personalInfo = ShortClosedPersonalInfo().personalInfoToShortClosedPersonalInfo(profile.personal_info)
        return this
    }
}