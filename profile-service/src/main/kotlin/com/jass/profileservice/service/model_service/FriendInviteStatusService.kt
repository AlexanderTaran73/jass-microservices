package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.FriendInviteStatus

interface FriendInviteStatusService {

    fun findByName(name: String): FriendInviteStatus?
}