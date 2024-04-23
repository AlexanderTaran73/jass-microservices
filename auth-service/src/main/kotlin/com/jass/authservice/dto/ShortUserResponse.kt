package com.jass.authservice.dto

import com.jass.authservice.dto.UserRole

class ShortUserResponse (
    val email: String,
    val password: String,
    val roles: MutableList<UserRole>
)