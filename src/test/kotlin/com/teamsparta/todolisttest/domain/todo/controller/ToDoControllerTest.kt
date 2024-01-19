package com.teamsparta.todolisttest.domain.todo.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.service.ToDoService
import com.teamsparta.todolisttest.infra.security.jwt.JwtPlugin
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockKExtension::class)
class ToDoControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    private val jwtPlugin: JwtPlugin
) : DescribeSpec({
    extension(SpringExtension)

    afterContainer {
        clearAllMocks()
    }

    val todoService = mockk<ToDoService>()

    describe("GET /todos/{id}") {
        context("존재하는 ID를 요청할 때 ") {
            it("200 status code를 응답한다.") {
                val todoId = 13L

                every { todoService.getToDoById(any()) } returns ToDoResponse(
                    id = todoId,
                    title = "test_title",
                    description = "asd",
                    date = LocalDateTime.now(),
                    status = "PROGRESS",
                    isCompleted = false,
                    isShared = false,
                    comments = mutableListOf()
                )

                val jwtToken = jwtPlugin.generateAccessToken(
                    subject = "8",
                    email = "a",
                    role = "ADMIN"
                )
                val result = mockMvc.perform(
                    get("/todos/$todoId")
                        .header("Authorization", "Bearer $jwtToken")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                ).andReturn()

                result.response.status shouldBe 200

                val responseDto = jacksonObjectMapper().readValue(
                    result.response.contentAsString,
                    ToDoResponse::class.java
                )

                responseDto.id shouldBe todoId
            }
        }
    }
})