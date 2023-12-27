package com.teamsparta.todolisttest.domain.todo.dto

data class UpdateToDoRequest(

    val title: String?,
    val description: String?,
    val isCompleted: Boolean?,
    val priority: Int?,
    val isShared: Boolean?

)

//제목
//내용
//완료됬는지
//우선순위
//공유