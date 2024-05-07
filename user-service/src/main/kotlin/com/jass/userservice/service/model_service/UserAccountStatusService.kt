package com.jass.userservice.service.model_service

import com.jass.userservice.model.UserAccountStatus

interface UserAccountStatusService {

    fun save(userRole: UserAccountStatus)

    fun findById(id: Int): UserAccountStatus?

    fun findByName(name: String): UserAccountStatus?
}