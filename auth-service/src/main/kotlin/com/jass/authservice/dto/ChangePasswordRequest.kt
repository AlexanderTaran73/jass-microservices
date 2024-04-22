package com.jass.authservice.dto


class ChangePasswordRequest (
    val email: String,
    val oldPassword: String,
    val newPassword: String
)
