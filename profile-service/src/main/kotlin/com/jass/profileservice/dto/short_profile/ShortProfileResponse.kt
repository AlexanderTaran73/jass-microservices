package com.jass.profileservice.dto.short_profile

import com.jass.profileservice.dto.ImageInfoResponse
import com.jass.profileservice.module.Profile

open class ShortProfileResponse {
    open var id: Int = 0
    open var userName: String = ""
    open var avatar: String? = null

    fun profileToShortProfileResponse(profile: Profile, requestEmail: String, avatarName: String?): ShortProfileResponse {
        if (profile.profile_settings.profileVisibility!!.name == "PUBLIC" ||
            (profile.profile_settings.profileVisibility!!.name == "FRIENDS_ONLY" && profile.friends.any { friend -> friend.userEmail == requestEmail })
            ) return ShortPublicProfile().profileToShortPublicProfile(profile, avatarName)!!
        else return ShortClosedProfile().profileToShortClosedProfile(profile, avatarName)!!

    }
}