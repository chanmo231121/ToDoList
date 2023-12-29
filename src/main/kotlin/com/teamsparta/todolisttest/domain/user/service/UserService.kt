package com.teamsparta.todolisttest.domain.user.service

import com.teamsparta.todolisttest.domain.user.dto.SignUpRequest
import com.teamsparta.todolisttest.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.todolisttest.domain.user.dto.UserResponse

interface UserService {

        fun signUp(request: SignUpRequest): UserResponse

        fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse

}