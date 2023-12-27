package com.teamsparta.todolisttest.domain.user.dto

data class SignupRequest(
    val email: String,
    val password: String,
    val nickname: String,

)
