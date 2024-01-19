package com.teamsparta.todolisttest.domain.todo.model

import com.fasterxml.jackson.annotation.JsonCreator
import org.yaml.snakeyaml.util.EnumUtils

enum class ToDoStatus {

    COMPLETE,
    PROGRESS;


    companion object {
        @JvmStatic
        @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
        fun parse(name: String?): ToDoStatus? =
            name?.let { EnumUtils.findEnumInsensitiveCase(ToDoStatus::class.java,it.trim()) }
    }
}