package com.teamsparta.todolisttest.domain.todo.controller

import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todolisttest.domain.todo.dto.CreateToDoRequest
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.dto.UpdateToDoRequest
import com.teamsparta.todolisttest.domain.todo.exception.ModelNotFoundException
import com.teamsparta.todolisttest.domain.todo.service.ToDoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RequestMapping("/todos")
@RestController
class ToDoController(
    private val todoService: ToDoService
) {
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/search")
    fun searchTodoList(@RequestParam(value = "title") title: String): ResponseEntity<List<ToDoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.searchToDoList(title))
    }




    //단건조회
        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping("/{todoId}")
        fun getToDo(@PathVariable todoId: Long): ResponseEntity<ToDoResponse> {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getToDoById(todoId))

        }

    //목록조회
        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping()
        fun getPaginatedToDoList(
            @PageableDefault(
                size = 15,
                sort = ["id"]
            ) pageable: Pageable,
            @RequestParam(value = "status", required = false) status :String?
        ): ResponseEntity<Page<ToDoResponse>> {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getPaginatedToDoList(pageable,status))
        }

    // ToDo생성

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    fun createToDo(@RequestBody createTodoRequest: CreateToDoRequest): ResponseEntity<ToDoResponse> {
        //val userId = createTodoRequest.userId
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createToDo(createTodoRequest))

    }

    // ToDo수정
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{todoId}")
    fun updateToDo(
        @PathVariable todoId: Long,
        @RequestBody updateToDoRequest: UpdateToDoRequest
    ): ResponseEntity<ToDoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateToDo(todoId, updateToDoRequest))
    }

    // ToDo삭제
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{todoId}")
    fun deleteToDo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.deleteToDo((todoId))
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }


}

//서비스랑 뽀짝뽀짝한걸 호출하는거임  상태와 바디쪽으로 호출