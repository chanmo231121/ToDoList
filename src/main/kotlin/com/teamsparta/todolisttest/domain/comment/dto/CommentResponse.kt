package com.teamsparta.todolisttest.domain.comment.dto

import java.time.LocalDateTime

// CommentResponse: 코멘트에 대한 응답을 나타내는 데이터 클래스
data class CommentResponse(


    val description: String, // 코멘트의 내용을 담는 문자열(description)
    val date:LocalDateTime,  // 코멘트의 날짜/시간 정보를 담는 LocalDateTime 객체(date)
)





