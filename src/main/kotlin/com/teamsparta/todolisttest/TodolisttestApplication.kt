package com.teamsparta.todolisttest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class TodolisttestApplication

fun main(args: Array<String>) {
    runApplication<TodolisttestApplication>(*args)
}
