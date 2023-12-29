package com.teamsparta.todolisttest.domain.user.service

import com.teamsparta.todolisttest.domain.todo.exception.ModelNotFoundException
import com.teamsparta.todolisttest.domain.user.dto.SignUpRequest
import com.teamsparta.todolisttest.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.todolisttest.domain.user.dto.UserResponse
import com.teamsparta.todolisttest.domain.user.model.Profile
import com.teamsparta.todolisttest.domain.user.model.User
import com.teamsparta.todolisttest.domain.user.model.UserRole
import com.teamsparta.todolisttest.domain.user.model.toResponse

import com.teamsparta.todolisttest.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun signUp(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = request.email,
                // TODO: 비밀번호 암호화
                password = request.password,
                profile = Profile(
                    nickname = request.nickname
                ),
                role = when (request.role) {
                    UserRole.PERSON.name -> UserRole.PERSON
                    UserRole.ADMIN.name -> UserRole.ADMIN
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).toResponse()

    }

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = request.nickname
        )

        return userRepository.save(user).toResponse()
    }
}

// UserServiceImpl.kt