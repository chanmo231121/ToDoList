package com.teamsparta.todolisttest.domain.user.model


import com.teamsparta.todolisttest.domain.todo.model.ToDo
import com.teamsparta.todolisttest.domain.user.dto.UserResponse
import jakarta.persistence.*


@Entity
@Table(name = "app_user")
class User(

    @Column(name = "email")
    val email: String,

    @Column(name = "password")
    val password: String,

    @Embedded
    var profile: Profile,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: UserRole,


    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var todos: MutableList<ToDo> = mutableListOf(),




) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}


fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id!!,
        nickname = profile.nickname,
        email = email,
        role = role.name,
    )
}