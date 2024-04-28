package com.jass.profileservice.repository

import com.jass.profileservice.module.FriendInviteStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FriendInviteStatusRepository: JpaRepository<FriendInviteStatus, Int> {

    fun findByName(name: String): FriendInviteStatus?
}