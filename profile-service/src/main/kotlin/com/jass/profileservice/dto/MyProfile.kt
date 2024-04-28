package com.jass.profileservice.dto

import com.jass.profileservice.module.ImageInfo
import com.jass.profileservice.module.Profile
import com.jass.profileservice.service.model_service.FriendInviteService


class MyProfile(
    private val friendInviteService: FriendInviteService
): ProfileResponse(){
    override var id: Int = 0
    var userEmail = ""
    override var userName: String = ""
    var personal_info: MyPersonalInfo? = null
// TODO: move to image-service
    override var avatar: ImageInfo? = null
    var images: MutableList<ImageInfo?> = mutableListOf()
    var profile_settings: MyProfileSettings? = null
    var friendsIds: MutableList<Int> = mutableListOf()

    var my_friend_inviting: MutableList<Int> = mutableListOf()
    var friend_inviting_me: MutableList<Int> = mutableListOf()

    fun profileToMyProfileResponse(profile: Profile): MyProfile {
        id = profile.id
        userEmail = profile.userEmail
        userName = profile.userName
        personal_info = MyPersonalInfo().personalInfoToMyPersonalInfo(profile.personal_info)
//        avatar = profile.avatar TODO: get avatar
        images = profile.images
        profile_settings = MyProfileSettings().profileSettingsMyProfileSettings(profile.profile_settings)
        profile.friends.forEach { friend -> friendsIds.add(friend.id) }
        my_friend_inviting = friendInviteService.findAllByInviterId(profile.id).map { it.invitedId }.toMutableList()
        friend_inviting_me = friendInviteService.findAllByInvitedId(profile.id).map { it.inviterId }.toMutableList()

        return this
    }
}