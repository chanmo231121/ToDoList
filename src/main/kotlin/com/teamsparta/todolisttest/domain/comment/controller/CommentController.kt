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
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos/{todoId}/comment")
@RestController

// @RestController: 이 클래스는 REST 요청을 처리하는 컨트롤러임을 나타낸다.
// @RequestMapping("/todos/{todoId}/comment"): 이 컨트롤러의 모든 엔드포인트는 "/todos/{todoId}/comment" 경로에 매핑.

class CommentController (
   private val commentService: CommentService

    // CommentController 클래스는 CommentService를 주입받아 생성
){

    //comment 단건조회
    // @GetMapping("/{commentId}"): 특정 commentId에 해당하는 코멘트를 조회
    @PreAuthorize("hasRole('ADMIN') and hasRole('PERSON')")
    @GetMapping("/{commentId}")
    fun getComment(@PathVariable todoId: Long, commentId: Long): ResponseEntity<CommentResponse> {

        // ResponseEntity: HTTP 응답을 나타내는 Spring의 클래스

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentById(todoId,commentId))

        // HTTP 상태 코드를 설정 (200 OK).
        // commentService를 사용하여 todoId와 commentId에 해당하는 코멘트를 조회하고, 응답 본문에 설정

    }

    //comment목록조회
    // @GetMapping(): 특정 todoId에 해당하는 모든 코멘트 리스트를 조회한다
    @PreAuthorize("hasRole('ADMIN') or hasRole('PERSON')")
    @GetMapping()
    fun getCommentList(@PathVariable todoId: Long): ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getAllCommentList(todoId))

        // HTTP 상태 코드를 설정 (200 OK).
        // commentService를 사용하여 todoId에 해당하는 모든 코멘트 리스트를 응답 본문에 설정
    }

    // comment 생성
    // @PostMapping(): 특정 todoId에 대한 새로운 코멘트를 생성
    @PreAuthorize("hasRole('ADMIN') or hasRole('PERSON')")
    @PostMapping()
    fun createComment(@PathVariable todoId: Long,
                      @RequestBody createCommentRequest: CreateCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(todoId,createCommentRequest))
        // HTTP 상태 코드를 설정합니다 (201 Created).
        // commentService를 사용하여 todoId에 대한 createCommentRequest를 이용해 새로운 코멘트를 생성하고, 응답 본문에 설정
    }

    // comment수정
    // @PutMapping("/{commentId}"): 특정 todoId에 대한 특정 commentId의 코멘트를 업데이트
    @PreAuthorize("hasRole('ADMIN') or hasRole('PERSON')")
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable commentId: Long,
        @PathVariable todoId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, updateCommentRequest))

        // HTTP 상태 코드를 설정합니다 (200 OK).
        // commentService를 사용하여 todoId에 대한 commentId의 코멘트를 updateCommentRequest를 이용해 업데이트하고, 응답 본문에 설정
    }

    // comment삭제
    // @DeleteMapping("/{commentId}"): 특정 commentId의 코멘트를 삭제
    @PreAuthorize("hasRole('ADMIN') ")
    @DeleteMapping("/{commentId}")

    fun deleteComment( @PathVariable todoId: Long ,@PathVariable commentId: Long, @RequestBody password:String): ResponseEntity<Unit> {
        commentService.deleteComment(todoId, commentId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()

        // HTTP 상태 코드를 설정한다 (204 No Content).
        // 응답 본문을 생성하지 않는다
    }

}