package com.teamsparta.todolisttest.domain.todo.service

import com.teamsparta.todolisttest.domain.todo.dto.CreateToDoRequest
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.dto.UpdateToDoRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class TodoServiceImpl :ToDoService {

    override fun getAllToDoList(): List<ToDoResponse> {
        TODO("Not yet implemented")
        //DB에서 모든 TodoList를 가져와서 CourseResponse로 변환 후 반환
    }


    override fun getToDoById(todoId: Long): ToDoResponse {
        //만약 todoId에 해당하는 todolist가 없다면 throw ModelNotFoundException
        //DB에서 todoId에 해당하는 todolist를 가져와서 TodoResponse로 변환 후 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun createToDo(request: CreateToDoRequest): ToDoResponse {
        //request를 todoresponse로 변환 후 DB에 저장
        //중간에 예외가 발생했을 때, 일부만 수행되면 안되서 Transactional 사용
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateToDo(todoId: Long, request: UpdateToDoRequest): ToDoResponse {
        // 만약 todoId에 해당하는 todolist가 없다면 throw ModelNotFoundException
        // DB에서 todoId에 해당하는 todolist를 가져와서 request로 업데이트 후 DB에 저장, 결과를 todoResponse로 변환 후 반환
        //중간에 예외가 발생했을 때, 일부만 수행되면 안되서 Transactional 사용
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteToDo(todoId: Long) {
        // 만약 todoId에 해당하는 todolist가 없다면 throw ModelNotFoundException
        // DB에서 todoId에 해당하는 todolist를 삭제
        //중간에 예외가 발생했을 때, 일부만 수행되면 안되서 Transactional 사용
        TODO("Not yet implemented")
    }

}