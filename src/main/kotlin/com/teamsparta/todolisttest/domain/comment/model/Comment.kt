package com.teamsparta.todolisttest.domain.comment.model

import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.todo.model.ToDo
import com.teamsparta.todolisttest.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "description")
    var description: String,

    @Column(name = "created_at")
    var createdAt: LocalDateTime,

    @Column(name = "comment_todo_id")
    var todoId: String,

    @Column(name = "comment_user_id")
    var userId: String,




) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    val todo: ToDo? = null


}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id,
        description = description,
        createdAt = createdAt,
        todoId = todoId,
        userId = userId,


        )
}