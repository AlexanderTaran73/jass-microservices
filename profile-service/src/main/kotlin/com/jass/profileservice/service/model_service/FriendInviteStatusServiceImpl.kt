package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.FriendInviteStatus
import com.jass.profileservice.repository.FriendInviteStatusRepository
import org.springframework.stereotype.Service

@Service
class FriendInviteStatusServiceImpl(
    private val friendInviteStatusRepository: FriendInviteStatusRepository
): FriendInviteStatusService {
    override fun findByName(name: String): FriendInviteStatus? {
        return friendInviteStatusRepository.findByName(name)
    }
}