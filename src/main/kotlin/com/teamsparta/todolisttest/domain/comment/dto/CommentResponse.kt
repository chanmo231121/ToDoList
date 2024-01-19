package com.teamsparta.todolisttest.domain.comment.dto

import java.time.LocalDateTime

// CommentResponse: 코멘트에 대한 응답을 나타내는 데이터 클래스
data class CommentResponse(

    val id: Long,
    val description: String,
    val createAt: LocalDateTime,

)





