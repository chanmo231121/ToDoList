package com.teamsparta.todolisttest.domain.todo.controller

import com.teamsparta.todolisttest.domain.todo.dto.CreateToDoRequest
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.dto.UpdateToDoRequest
import com.teamsparta.todolisttest.domain.todo.exception.ModelNotFoundException
import com.teamsparta.todolisttest.domain.todo.service.ToDoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

//


@RequestMapping("/todos")
@RestController
class ToDoController (
    private val todoService: ToDoService
){


    //단건조회
    @GetMapping("/{todoId}")
    fun getToDo(@PathVariable todoId: Long): ResponseEntity<ToDoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getToDoById(todoId))

    }

    //목록조회
    @GetMapping()
    fun getToDoList(): ResponseEntity<List<ToDoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllToDoList())
    }

    // ToDo생성
    @PostMapping
    fun createToDo(@RequestBody createTodoRequest: CreateToDoRequest): ResponseEntity<ToDoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createToDo(createTodoRequest))
    }

    // ToDo수정
    @PutMapping("/{todoId}")
    fun updateToDo(
        @PathVariable todoId: Long,
        @RequestBody updateToDoRequest: UpdateToDoRequest
    ): ResponseEntity<ToDoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateToDo(todoId,updateToDoRequest))
    }

    // ToDo삭제
    @DeleteMapping("/{todoId}")
    fun deleteToDo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e:ModelNotFoundException):ResponseEntity<com.teamsparta.todolisttest.domain.todo.exception.dto.ErrorResponse>{
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(com.teamsparta.todolisttest.domain.todo.exception.dto.ErrorResponse(e.message))
    }
    //ErrorResponse 가 스프링이 있고 exception에 dto로 만든게 있다 지금은 만든 dto 확인해보기



}