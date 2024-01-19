package com.teamsparta.todolisttest.domain.comment.repository
import com.teamsparta.todolisttest.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository:JpaRepository<Comment,Long> {
    fun findByTodoIdAndId(todoId: Long , commentId: Long): Comment?
}