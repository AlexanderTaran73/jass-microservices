package com.jass.profileservice.dto

import com.jass.profileservice.module.ImageInfo
import com.jass.profileservice.module.Profile

open class ProfileResponse {
    open var id: Int = 0
    open var userName: String = ""
    open var avatar: ImageInfo? = null

    fun profileToProfileResponse(profile: Profile, requestEmail: String): ProfileResponse {
        if (profile.profile_settings.profileVisibility!!.name == "PUBLIC") return PublicProfile().profileToPublicProfile(profile)!!
        else if (profile.profile_settings.profileVisibility!!.name == "FRIENDS_ONLY" && profile.friends.any { friend -> friend.userEmail == requestEmail }) return PublicProfile().profileToPublicProfile(profile)!!
        else return ClosedProfile().profileToClosedProfile(profile)!!

    }
}