package com.teamsparta.todolisttest.domain.user.repository

import com.teamsparta.todolisttest.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean
}