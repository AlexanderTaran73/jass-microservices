package com.jass.profileservice.service.controller_service

import com.jass.profileservice.module.FriendInvite
import com.jass.profileservice.service.model_service.FriendInviteService
import com.jass.profileservice.service.model_service.FriendInviteStatusService
import com.jass.profileservice.service.model_service.ProfileService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class FriendsService(
    private val profileService: ProfileService,
    private val friendInviteService: FriendInviteService,
    private val friendInviteStatusService: FriendInviteStatusService
) {
    fun sendRequest(email: String, id: Int): ResponseEntity<HttpStatus> {
//        if the profile is not found
        val profile = profileService.findByUserEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val recipient = profileService.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
//        if  already friends
        if(recipient in profile.friends) return ResponseEntity(HttpStatus.BAD_REQUEST)
//        if the recipient has already made a request
        if (friendInviteService.findByInviterIdAndInvitedId(id, profile.id) != null) {
            val friendInvite = friendInviteService.findByInviterIdAndInvitedId(id, profile.id)!!

            friendInviteService.delete(friendInvite)

            profile.friends.add(recipient)
            recipient.friends.add(profile)

            profileService.save(profile)
            profileService.save(recipient)
            return ResponseEntity(HttpStatus.CREATED)
        }
        else {
            val friendInvite = friendInviteService.findByInviterIdAndInvitedId(profile.id, id) ?: FriendInvite()
            friendInvite.inviterId = profile.id
            friendInvite.invitedId = id
            if (friendInvite.status == null || friendInvite.status!!.name != "REJECTED") friendInvite.status = friendInviteStatusService.findByName("SENT")!!

            friendInviteService.save(friendInvite)
            return ResponseEntity(HttpStatus.CREATED)
        }
    }

    fun acceptRequest(email: String, id: Int): ResponseEntity<HttpStatus> {
        val profile = profileService.findByUserEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val recipient = profileService.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val friendInvite = friendInviteService.findByInviterIdAndInvitedId(id, profile.id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        friendInviteService.delete(friendInvite)

        profile.friends.add(recipient)
        recipient.friends.add(profile)

        profileService.save(profile)
        profileService.save(recipient)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    fun rejectRequest(email: String, id: Int): ResponseEntity<HttpStatus> {
        val profile = profileService.findByUserEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val recipient = profileService.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val friendInvite = friendInviteService.findByInviterIdAndInvitedId(id, profile.id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        friendInvite.status = friendInviteStatusService.findByName("REJECTED")!!

        friendInviteService.save(friendInvite)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    fun cancelRequest(email: String, id: Int): ResponseEntity<HttpStatus> {
        val profile = profileService.findByUserEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val recipient = profileService.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        val friendInvite = friendInviteService.findByInviterIdAndInvitedId(profile.id, id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        friendInviteService.delete(friendInvite)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    fun deleteFromFriends(email: String, id: Int): ResponseEntity<HttpStatus> {
        val profile = profileService.findByUserEmail(email) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        val recipient = profileService.findById(id) ?: return ResponseEntity(HttpStatus.NOT_FOUND)

        profile.friends.remove(recipient)
        recipient.friends.remove(profile)

        profileService.save(profile)
        profileService.save(recipient)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }




}