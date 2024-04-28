package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.FriendInvite

interface FriendInviteService {

    fun createFriendInvite(inviterId: Int, invitedId: Int): FriendInvite

    fun save(friendInvite: FriendInvite)

    fun delete(friendInvite: FriendInvite)

    fun findByInviterIdAndInvitedId(inviterId: Int, invitedId: Int): FriendInvite?

    fun findAllByInviterId(inviterId: Int): List<FriendInvite>

    fun findAllByInvitedId(invitedId: Int): List<FriendInvite>

}