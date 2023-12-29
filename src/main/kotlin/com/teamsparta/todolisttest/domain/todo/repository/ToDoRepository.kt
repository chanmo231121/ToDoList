package com.teamsparta.todolisttest.domain.todo.repository

import com.teamsparta.todolisttest.domain.todo.model.ToDo
import org.springframework.data.jpa.repository.JpaRepository

interface ToDoRepository : JpaRepository<ToDo,Long>{



}