package com.teamsparta.todolisttest.domain.todo.service

import com.teamsparta.todolisttest.domain.todo.dto.CreateToDoRequest
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.dto.UpdateToDoRequest
import org.springframework.stereotype.Component



interface ToDoService {


    fun getAllToDoList():List<ToDoResponse>

    fun getToDoById(todoId:Long):ToDoResponse

    fun createToDo(request: CreateToDoRequest):ToDoResponse

    fun updateToDo(todoId: Long, request: UpdateToDoRequest):ToDoResponse

    fun deleteToDo(todoId: Long)



}

