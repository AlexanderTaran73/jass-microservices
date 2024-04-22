package com.jass.authservice.service.model_service

import com.jass.authservice.model.ChangePasswordData

interface ChangePasswordDataService {

    fun save(changePasswordData: ChangePasswordData)

    fun delete(changePasswordData: ChangePasswordData)

    fun findByEmail(email: String): ChangePasswordData?


}