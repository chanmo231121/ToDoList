package com.teamsparta.todolisttest.domain.comment.service

import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todolisttest.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun getAllCommentList(todoId: Long): List<CommentResponse>

    fun getCommentById(todoId: Long, commentId:Long) : CommentResponse

    fun createComment(todoId: Long, request: CreateCommentRequest): CommentResponse

    fun updateComment(todoId:Long, commentId: Long,request: UpdateCommentRequest):CommentResponse

    fun deleteComment(todoId: Long, commentId: Long){

    }


}