package com.teamsparta.todolisttest.domain.comment.dto

import com.teamsparta.todolisttest.domain.todo.model.ToDo

data class CreateCommentRequest(

    val userId: Long,
    val description:String,

)
