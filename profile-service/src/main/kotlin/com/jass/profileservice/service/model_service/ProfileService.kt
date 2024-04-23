package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.Profile

interface ProfileService {

    fun save(profile: Profile)

    fun delete(profile: Profile)

    fun findByUserEmail(userEmail: String): Profile?
}