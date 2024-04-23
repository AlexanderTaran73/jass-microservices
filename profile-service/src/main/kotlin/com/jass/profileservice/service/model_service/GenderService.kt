package com.jass.profileservice.service.model_service

import com.jass.profileservice.module.Gender

interface GenderService {

    fun findById(id: Int): Gender?

}