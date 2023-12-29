package com.teamsparta.todolisttest.domain.user.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable


@Embeddable
data class Profile (

    @Column(name = "nickname")
    var nickname:String
)