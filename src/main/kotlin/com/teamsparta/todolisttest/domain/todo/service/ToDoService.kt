package com.teamsparta.todolisttest.domain.todo.service

import com.teamsparta.todolisttest.domain.todo.dto.CreateToDoRequest
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.dto.UpdateToDoRequest
import com.teamsparta.todolisttest.domain.todo.model.ToDo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface ToDoService {

    fun getAllToDoList():List<ToDoResponse>
    fun getToDoById(todoId:Long):ToDoResponse
    fun createToDo(request: CreateToDoRequest):ToDoResponse
    fun updateToDo(todoId: Long, request: UpdateToDoRequest):ToDoResponse
    fun deleteToDo(todoId: Long)
    fun searchToDoList(title:String):List<ToDoResponse>?
    fun getPaginatedToDoList(pageable: Pageable, status: String?): Page<ToDoResponse>?

}

//설계도
