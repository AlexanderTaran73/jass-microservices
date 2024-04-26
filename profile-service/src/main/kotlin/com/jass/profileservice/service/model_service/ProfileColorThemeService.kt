package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ProfileColorTheme

interface ProfileColorThemeService {

    fun findByName(name: String): ProfileColorTheme?
}