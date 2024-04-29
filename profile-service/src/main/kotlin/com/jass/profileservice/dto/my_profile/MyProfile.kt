package com.jass.profileservice.dto.my_profile

import com.fasterxml.jackson.annotation.JsonIgnore
import com.jass.profileservice.dto.ImageInfoResponse
import com.jass.profileservice.feign.ImageService
import com.jass.profileservice.module.Profile
import com.jass.profileservice.service.model_service.FriendInviteService


class MyProfile(
    @JsonIgnore private val friendInviteService: FriendInviteService,
    @JsonIgnore private val imageService: ImageService
){
    var id: Int = 0
    var userEmail = ""
    var userName: String = ""
    var personal_info: MyPersonalInfo? = null
    var avatar: String? = null
    var images: MutableList<String> = mutableListOf()
    var profile_settings: MyProfileSettings? = null
    var friendsIds: MutableList<Int> = mutableListOf()

    var my_friend_inviting: MutableList<Int> = mutableListOf()
    var friend_inviting_me: MutableList<Int> = mutableListOf()

    fun profileToMyProfileResponse(profile: Profile): MyProfile {
        id = profile.id
        userEmail = profile.userEmail
        userName = profile.userName
        personal_info = MyPersonalInfo().personalInfoToMyPersonalInfo(profile.personal_info)

        try {
            avatar = imageService.getImageInfo("ProfileAvatar", profile.id).body!!.get(0).fileName
        } catch (e: Exception) {
            avatar = null
        }

        try {
            val imageList = imageService.getImageInfo("ProfileImage", profile.id).body!!

            imageList.forEach { image ->
                images.add(image.fileName)
            }
        } catch (e: Exception) { }



        profile_settings = MyProfileSettings().profileSettingsMyProfileSettings(profile.profile_settings)
        profile.friends.forEach { friend -> friendsIds.add(friend.id) }
        my_friend_inviting = friendInviteService.findAllByInviterId(profile.id).map { it.invitedId }.toMutableList()
        friend_inviting_me = friendInviteService.findAllByInvitedId(profile.id).map { it.inviterId }.toMutableList()

        return this
    }
}