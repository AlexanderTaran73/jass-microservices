package com.jass.profileservice.dto

import com.jass.profileservice.module.ImageInfo
import com.jass.profileservice.module.Profile

class PublicProfile : ProfileResponse() {
    override var id: Int = 0
    var userEmail = ""
    override var userName: String = ""
    var personal_info: PublicPersonalInfo? = null
// TODO: move to image-service
    override var avatar: ImageInfo? = null
    var images: MutableList<ImageInfo?> = mutableListOf()
    var friendsIds: MutableList<Int> = mutableListOf()

    fun profileToPublicProfile(profile: Profile?): PublicProfile?{
        if (profile == null) return null
        id = profile.id
        userEmail = profile.userEmail
        userName = profile.userName
        personal_info = PublicPersonalInfo().personalInfoToPublicPersonalInfo(profile.personal_info)
//        avatar = profile.avatar TODO: get avatar
        images = profile.images
        profile.friends.forEach { friend -> friendsIds.add(friend.id) }
        return this
    }
}