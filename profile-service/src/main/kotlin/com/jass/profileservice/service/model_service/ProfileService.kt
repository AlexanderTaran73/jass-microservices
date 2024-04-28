package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.Profile

interface ProfileService {
// TODO: create profile
    fun save(profile: Profile)

    fun delete(profile: Profile)

    fun findAll(): List<Profile>

    fun findByUserEmail(userEmail: String): Profile?
}