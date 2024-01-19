package com.teamsparta.todolisttest.domain.todo.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.Order
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.EntityPathBase
import com.querydsl.core.types.dsl.PathBuilder
import com.teamsparta.todolisttest.domain.comment.model.QComment
import com.teamsparta.todolisttest.domain.todo.dto.ToDoResponse
import com.teamsparta.todolisttest.domain.todo.model.QToDo
import com.teamsparta.todolisttest.domain.todo.model.ToDo
import com.teamsparta.todolisttest.domain.todo.model.ToDoStatus
import com.teamsparta.todolisttest.domain.todo.model.toResponse
import com.teamsparta.todolisttest.infra.querydsl.QueryDslSupport
import jakarta.persistence.EntityManager
import jakarta.persistence.EntityNotFoundException
import jakarta.persistence.PersistenceContext
import org.aspectj.weaver.ast.Or
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.beans.Expression

@Repository
class ToDoRepositoryImpl : QueryDslSupport(),CustomToDoRepository {

    private val todo = QToDo.toDo

    override fun searchToDoListByTitle(title: String): List<ToDo> {
        return queryFactory.selectFrom(todo)
            .where(todo.title.containsIgnoreCase(title))
            .fetch()

    }

    override fun findByPageableAndStatus(pageable: Pageable, todoStatus: ToDoStatus?): Page<ToDo> {

        val whereClause = BooleanBuilder()

        todoStatus?.let { whereClause.and(todo.status.eq(todoStatus)) }

        val totalCount = queryFactory.select(todo.count()).from(todo).where(whereClause).fetchOne() ?: 0L

        val comment = QComment.comment
        val query = queryFactory.selectFrom(todo)
            .where(whereClause)
            .leftJoin(todo.comments, comment)
            .fetchJoin()
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())

        // order가 존재하는지 확인
        if (pageable.sort.isSorted) {
            // 정렬 기준 설정
            when (pageable.sort.first()?.property) {
                "id" -> query.orderBy(todo.id.asc())
                "title" -> query.orderBy(todo.title.asc())
                else -> query.orderBy(todo.id.asc())
            }
        } else {
            query.orderBy(todo.id.asc())
        }
        // 최종적으로 쿼리 수행
        val contents = query.fetch()

        // Page 구현체 반환
        return PageImpl(contents, pageable, totalCount)
    }

    }

/*
   private fun getOrderSpecifier(pageable: Pageable):Array<OrderSpecifier<*>>{
       val pathBuilder = PathBuilder(todo.type,todo.metadata)

       return pageable.sort.toList().map {
           order -> OrderSpecifier(
               if (order.isAscending) Order.ASC else Order.DESC,
               pathBuilder.get(order.property) as Expression<Comparable<*>>
           )
       }.toTypedArray()
    }
}*/
