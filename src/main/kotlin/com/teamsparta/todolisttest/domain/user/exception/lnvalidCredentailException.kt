package com.teamsparta.todolisttest.domain.user.exception

data class InvalidCredentialException (
    override val message: String? = "the credential is invalid"
):RuntimeException()