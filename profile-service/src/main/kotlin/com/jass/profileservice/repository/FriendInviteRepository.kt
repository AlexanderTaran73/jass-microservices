package com.jass.profileservice.repository

import com.jass.profileservice.module.FriendInvite
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendInviteRepository: JpaRepository<FriendInvite, Int> {

    fun findByInviterIdAndInvitedId(inviterId: Int, invitedId: Int): FriendInvite?
}