package com.teamsparta.todolisttest.domain.todo.model

import com.teamsparta.todolisttest.domain.comment.model.Comment
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.user.model.User
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "todo")
class ToDo(


    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String,

    @Column(name = "iscompleted", nullable = false)
    var isCompleted: Boolean = false,

    @Column(name = "isShared")
    var isShared: Boolean = false,

    @Column(name = "date")
    var date: LocalDateTime,

    @Column(name = "name")
    var name: String,


    @Column(name = "user_id")
    var userId: Long,

    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    var comments: MutableList<Comment> = mutableListOf(),


 /*   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User? = null*/



    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}



//nullable = false 은 반드시 값이 있어야한다

fun ToDo.toResponse():ToDoResponse{
    return ToDoResponse(
        id=id!!,
        title = title,
        description = description,
        date = date,
        isCompleted = isCompleted,
        isShared = isShared,


    )
}
