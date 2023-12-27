package com.teamsparta.todolisttest.domain.todo.dto

data class CreateToDoRequest(

    val title : String,
    val description: String,
    val priority: Int,
    val isShared: Boolean,

)


//제목
//내용
//우선순위
//공유