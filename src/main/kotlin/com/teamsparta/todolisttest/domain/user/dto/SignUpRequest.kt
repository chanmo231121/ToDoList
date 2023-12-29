package com.teamsparta.todolisttest.domain.user.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val role: String,
)
