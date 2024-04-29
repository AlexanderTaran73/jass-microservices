package com.jass.profileservice.dto.short_profile

import com.jass.profileservice.dto.ImageInfoResponse
import com.jass.profileservice.module.Profile

class ShortPublicProfile : ShortProfileResponse() {
    override var id: Int = 0
    var userEmail = ""
    override var userName: String = ""
    var personal_info: ShortPublicPersonalInfo? = null
    override var avatar: String? = null
    var friendsIds: MutableList<Int> = mutableListOf()

    fun profileToShortPublicProfile(profile: Profile?, avatarName: String?): ShortPublicProfile?{
        if (profile == null) return null
        id = profile.id
        userEmail = profile.userEmail
        userName = profile.userName
        personal_info = ShortPublicPersonalInfo().personalInfoToShortPublicPersonalInfo(profile.personal_info)
        avatar = avatarName
        profile.friends.forEach { friend -> friendsIds.add(friend.id) }
        return this
    }
}