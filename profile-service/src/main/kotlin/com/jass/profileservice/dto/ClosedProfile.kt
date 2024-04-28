package com.jass.profileservice.dto

import com.jass.profileservice.module.ImageInfo
import com.jass.profileservice.module.Profile

class ClosedProfile: ProfileResponse() {
    override var id: Int = 0
    override var userName: String = ""
    override var avatar: ImageInfo? = null

    fun profileToClosedProfile(profile: Profile?): ClosedProfile?{
        if (profile == null) return null
        id = profile.id
        userName = profile.userName
//        avatar = profile.avatar TODO: get avatar
        return this
    }
}