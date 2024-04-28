package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.FriendInvite
import com.jass.profileservice.repository.FriendInviteRepository
import com.jass.profileservice.repository.FriendInviteStatusRepository
import org.springframework.stereotype.Service

@Service
class FriendInviteServiceImpl(
    private val friendInviteRepository: FriendInviteRepository,
    private val friendInviteStatusRepository: FriendInviteStatusRepository
): FriendInviteService {
    override fun createFriendInvite(inviterId: Int, invitedId: Int): FriendInvite {
        val friendInvite = FriendInvite().also { invite ->
            invite.inviterId = inviterId
            invite.invitedId = invitedId
            invite.status = friendInviteStatusRepository.findByName("SENT")
        }
        friendInviteRepository.save(friendInvite)
        return friendInvite
    }

    override fun save(friendInvite: FriendInvite) {
        friendInviteRepository.save(friendInvite)
    }

    override fun delete(friendInvite: FriendInvite) {
        friendInviteRepository.delete(friendInvite)
    }

    override fun findByInviterIdAndInvitedId(inviterId: Int, invitedId: Int): FriendInvite? {
        return friendInviteRepository.findByInviterIdAndInvitedId(inviterId, invitedId)
    }
}