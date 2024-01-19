package com.teamsparta.todolisttest.domain.comment.exception

data class CommentNotFoundException (val commentName:String ,val commentId:Long)
    : RuntimeException(" $commentName not found with given id: $commentId")