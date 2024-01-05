package com.teamsparta.todolisttest.domain.user.controller

import com.teamsparta.todolisttest.domain.user.dto.SignUpRequest
import com.teamsparta.todolisttest.domain.user.dto.UpdateUserProfileRequest
import com.teamsparta.todolisttest.domain.user.dto.UserResponse
import com.teamsparta.todolisttest.domain.user.service.UserServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController


@RestController
class UserController(
    private val userService: UserServiceImpl
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signupRequest: SignUpRequest): ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.signUp(signupRequest))
    }


    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(@PathVariable userId: Long,
                          @RequestBody updateUserProfileRequest: UpdateUserProfileRequest):
            ResponseEntity<UserResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.updateUserProfile(userId,updateUserProfileRequest))

    }
}