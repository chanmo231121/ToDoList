package com.teamsparta.todolisttest.domain.comment.model

import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.todo.model.ToDo
import com.teamsparta.todolisttest.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "name")
    var name: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "created_at")
    var date: LocalDateTime =LocalDateTime.now(),

    @Column(name = "todo_id")
    var todoId: Long,

    @Column(name = "password")
    var password:String,









) {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null





}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(

        description = description,
        date = date,


        )
}