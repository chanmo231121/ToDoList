package com.teamsparta.todolisttest.domain.todo.dto

import java.time.LocalDateTime


data class ToDoResponse(

    val nickname: Long,
    val title: String,
    val description: String,
    val isCompleted : Boolean,
    val priority: Int,
    val isShared: Boolean,
    val time: LocalDateTime,



)


//id
//제목
//내용
//완료됬는지
//우선순위
//공유