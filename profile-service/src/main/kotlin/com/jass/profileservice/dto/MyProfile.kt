package com.jass.profileservice.dto

import com.jass.profileservice.module.ImageInfo
import com.jass.profileservice.module.Profile


class MyProfile: ProfileResponse(){
    override var id: Int = 0
    var userEmail = ""
    override var userName: String = ""
    var personal_info: MyPersonalInfo? = null
// TODO: move to image-service
    override var avatar: ImageInfo? = null
    var images: MutableList<ImageInfo?> = mutableListOf()
    var profile_settings: MyProfileSettings? = null
    var friendsIds: MutableList<Int> = mutableListOf()


    fun profileToMyProfileResponse(profile: Profile): MyProfile {
        id = profile.id
        userEmail = profile.userEmail
        userName = profile.userName
        personal_info = MyPersonalInfo().personalInfoToMyPersonalInfo(profile.personal_info)
//        avatar = profile.avatar TODO: get avatar
        images = profile.images
        profile_settings = MyProfileSettings().profileSettingsMyProfileSettings(profile.profile_settings)
        profile.friends.forEach { friend -> friendsIds.add(friend.id) }
        return this
    }
}