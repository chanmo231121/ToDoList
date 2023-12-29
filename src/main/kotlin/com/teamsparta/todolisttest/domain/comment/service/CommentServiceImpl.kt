package com.teamsparta.todolisttest.domain.comment.service

import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todolisttest.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todolisttest.domain.comment.model.Comment
import com.teamsparta.todolisttest.domain.comment.model.toResponse
import com.teamsparta.todolisttest.domain.comment.repository.CommentRepository
import com.teamsparta.todolisttest.domain.todo.exception.ModelNotFoundException
import com.teamsparta.todolisttest.domain.todo.repository.ToDoRepository
import com.teamsparta.todolisttest.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val toDoRepository: ToDoRepository,
    private val userRepository: UserRepository
) : CommentService {


    override fun getAllCommentList(): List<CommentResponse> {
        return commentRepository.findAll().map { it.toResponse() }
    }

    override fun getCommentById(commentId: Long): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        return comment.toResponse()
    }

    @Transactional
    override fun createComment(request: CreateCommentRequest): CommentResponse {
        return commentRepository.save(
            Comment(
                description = request.description,
                todoId = request.todoId,
                userId = request.userId,
                createdAt = LocalDateTime.now(),
                )
        ).toResponse()
    }

    @Transactional
    override fun updateComment(commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        val (description) = request

        comment.description = description
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun deleteComment(commentId: Long) {
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)
        commentRepository.delete(comment)
    }
}