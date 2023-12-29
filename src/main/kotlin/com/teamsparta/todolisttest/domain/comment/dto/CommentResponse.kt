package com.teamsparta.todolisttest.domain.comment.dto

import java.time.LocalDateTime


data class CommentResponse(
    val id : Long?,
    val description: String,
    val createdAt:LocalDateTime,
    val todoId:String,
    val userId:String

)
