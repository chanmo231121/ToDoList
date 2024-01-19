package com.teamsparta.todolisttest.domain.comment.service

import com.teamsparta.todolisttest.domain.comment.model.toResponse
import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todolisttest.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todolisttest.domain.comment.exception.CommentNotFoundException
import com.teamsparta.todolisttest.domain.comment.repository.CommentRepository
import com.teamsparta.todolisttest.domain.todo.repository.ToDoRepository
import com.teamsparta.todolisttest.domain.user.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service



@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: ToDoRepository,
    private val userRepository: UserRepository

) : CommentService {

    override fun getAllCommentList(todoId: Long): List<CommentResponse> {

        val todo = todoRepository.findByIdOrNull(todoId) ?: throw CommentNotFoundException("todo", todoId)
        return todo.comments.map { it.toResponse() }
    }

    override fun getCommentById(todoId: Long, commentId: Long): CommentResponse {

        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)
            ?: throw CommentNotFoundException("Comment", commentId)
        return comment.toResponse()
    }

    @org.springframework.transaction.annotation.Transactional
    override fun createComment( todoId: Long, request: CreateCommentRequest): CommentResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw CommentNotFoundException("todo", todoId)
        val user = userRepository.findByIdOrNull(request.userId) ?: throw CommentNotFoundException("user", request.userId)
        val comment = com.teamsparta.todolisttest.domain.comment.model.Comment(
            description = request.description,
            todo = todo,
            user = user
            //  JWT
        )
        todo.createComment(comment)
        todoRepository.save(todo)
        return comment.toResponse()
    }

    @Transactional
    override fun updateComment(todoId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {

        val comment = commentRepository.findByTodoIdAndId(todoId, commentId)  ?: throw CommentNotFoundException("Comment", commentId)

        comment.description = request.description

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun deleteComment( todoId: Long, commentId: Long) {

        val todo = todoRepository.findByIdOrNull(todoId) ?: throw CommentNotFoundException("todo" , todoId)
        val comment = commentRepository.findByIdOrNull(commentId)
            ?: throw CommentNotFoundException("Comment", commentId)

        todo.deleteComment(comment)
        todoRepository.save(todo)
    }
}