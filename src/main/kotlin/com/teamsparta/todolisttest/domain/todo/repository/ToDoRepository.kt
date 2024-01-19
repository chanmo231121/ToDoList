package com.teamsparta.todolisttest.domain.todo.repository

import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.model.ToDo
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.jpa.repository.JpaRepository

interface ToDoRepository : JpaRepository<ToDo, Long>, CustomToDoRepository {


}