package com.teamsparta.todolisttest.domain.todo.dto

import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.user.model.User
import java.time.LocalDateTime


data class ToDoResponse(

    val id: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val isShared: Boolean,
    val date: LocalDateTime,
    val status: String,
    val comments: List<CommentResponse>


    )


//id
//제목
//내용
//완료됬는지
//우선순위
//공유
//날짜