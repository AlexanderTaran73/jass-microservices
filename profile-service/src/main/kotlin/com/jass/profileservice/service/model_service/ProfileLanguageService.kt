package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.ProfileLanguage

interface ProfileLanguageService {

    fun findByName(name: String): ProfileLanguage?
}