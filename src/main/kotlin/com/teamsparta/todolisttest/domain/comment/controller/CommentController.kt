package com.teamsparta.todolisttest.domain.comment.controller

import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todolisttest.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todolisttest.domain.comment.service.CommentService
import com.teamsparta.todolisttest.domain.todo.dto.CreateToDoRequest
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.dto.UpdateToDoRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos/{todoId}/comment")
@RestController
class CommentController (
   private val commentService: CommentService
){

    //comment 단건조회
    @GetMapping("/{commentId}")
    fun getComment(@PathVariable commentId: Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentById(commentId))

    }

    //comment목록조회
    @GetMapping()
    fun getCommentList(): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllCommentList())
    }

    // comment 생성
    @PostMapping()
    fun createComment(@RequestBody createCommentRequest: CreateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(createCommentRequest))
    }

    // comment수정
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(commentId, updateCommentRequest))
    }

    // comment삭제
    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}