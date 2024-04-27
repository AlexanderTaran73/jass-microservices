package com.jass.profileservice.dto

import com.jass.profileservice.module.ImageInfo
import com.jass.profileservice.module.Profile

class PublicProfile {
    var userEmail = ""
    var userName: String = ""
    var personal_info: PublicPersonalInfo? = null
// TODO: move to image-service
    var images: MutableList<ImageInfo?> = mutableListOf()

    fun profileToPublicProfile(profile: Profile?): PublicProfile?{
        if (profile == null) return null
        userEmail = profile.userEmail
        userName = profile.userName
        personal_info = PublicPersonalInfo().personalInfoToPublicPersonalInfo(profile.personal_info)
        images = profile.images
        return this
    }
}