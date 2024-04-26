package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ProfileVisibility

interface ProfileVisibilityService {

    fun findByName(name: String): ProfileVisibility?
}