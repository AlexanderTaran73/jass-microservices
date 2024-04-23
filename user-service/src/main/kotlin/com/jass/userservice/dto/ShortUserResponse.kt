package com.jass.userservice.dto

import com.jass.userservice.model.UserRole

class ShortUserResponse (
    val email: String,
    val password: String,
    val roles: MutableList<UserRole>
)