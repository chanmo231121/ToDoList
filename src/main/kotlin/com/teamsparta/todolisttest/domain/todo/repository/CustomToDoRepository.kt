package com.teamsparta.todolisttest.domain.todo.repository

import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.model.ToDo
import com.teamsparta.todolisttest.domain.todo.model.ToDoStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomToDoRepository {

    fun searchToDoListByTitle(title:String):List<ToDo>
    fun findByPageableAndStatus(pageable:Pageable,todoStatus: ToDoStatus?): Page<ToDo>


}