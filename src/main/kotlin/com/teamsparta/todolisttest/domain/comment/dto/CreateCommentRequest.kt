package com.teamsparta.todolisttest.domain.comment.dto

import com.teamsparta.todolisttest.domain.todo.model.ToDo

data class CreateCommentRequest(
    val userId:String,
    val todoId:String,
    val description:String,
    val todo: ToDo
)
