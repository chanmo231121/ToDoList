package com.teamsparta.todolisttest.domain.todo.service

import com.teamsparta.todolisttest.domain.comment.dto.CommentResponse
import com.teamsparta.todolisttest.domain.comment.dto.CreateCommentRequest
import com.teamsparta.todolisttest.domain.comment.model.Comment
import com.teamsparta.todolisttest.domain.comment.model.toResponse
import com.teamsparta.todolisttest.domain.todo.dto.CreateToDoRequest
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.dto.UpdateToDoRequest
import com.teamsparta.todolisttest.domain.todo.exception.ModelNotFoundException
import com.teamsparta.todolisttest.domain.todo.model.ToDo
import com.teamsparta.todolisttest.domain.todo.model.toResponse
import com.teamsparta.todolisttest.domain.todo.repository.ToDoRepository
import com.teamsparta.todolisttest.domain.user.repository.UserRepository
import org.apache.catalina.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime


@Service
class TodoServiceImpl(
    private val toDoRepository: ToDoRepository,
    private val userRepository: UserRepository,
) : ToDoService {

    override fun getAllToDoList(orderBy: String): List<ToDoResponse> {
        return toDoRepository.findAll().map { it.toResponse() }

        //DB에서 모든 TodoList를 가져와서 TodoResponse로 변환 후 반환
    }

    override fun getToDoById(todoId: Long): ToDoResponse {
        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Course", todoId)
        return todo.toResponse()


        //만약 todoId에 해당하는 todolist가 없다면 throw ModelNotFoundException
        //DB에서 todoId에 해당하는 todolist를 가져와서 TodoResponse로 변환 후 반환
    }

    @Transactional
    override fun createToDo(userId:Long, request: CreateToDoRequest): ToDoResponse {
        return toDoRepository.save(
            ToDo(
                title = request.title,
                name = request.name,
                description = request.description,
                date = LocalDateTime.now(),
                userId = userId



            )
        ).toResponse()

        //request를 todoresponse로 변환 후 DB에 저장
        //중간에 예외가 발생했을 때, 일부만 수행되면 안되서 Transactional 사용
    }



    @Transactional
    override fun updateToDo(todoId: Long, request: UpdateToDoRequest): ToDoResponse {

        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("Course", todoId)
        val (title, description) = request
        todo.title = title
        todo.description = description
        return toDoRepository.save(todo).toResponse()

        // 만약 todoId에 해당하는 todolist가 없다면 throw ModelNotFoundException
        // DB에서 todoId에 해당하는 todolist를 가져와서 request로 업데이트 후 DB에 저장, 결과를 todoResponse로 변환 후 반환
        //중간에 예외가 발생했을 때, 일부만 수행되면 안되서 Transactional 사용
    }

    @Transactional
    override fun deleteToDo(todoId: Long) {

        val todo = toDoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("ToDo", todoId)
        toDoRepository.delete(todo)

        // 만약 todoId에 해당하는 todolist가 없다면 throw ModelNotFoundException
        // DB에서 todoId에 해당하는 todolist를 삭제
        //중간에 예외가 발생했을 때, 일부만 수행되면 안되서 Transactional 사용

    }


}