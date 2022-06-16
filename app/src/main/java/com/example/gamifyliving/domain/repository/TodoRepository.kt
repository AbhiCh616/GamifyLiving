package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.entity.Todo
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun observe(): Flow<List<Todo>>
    suspend fun getById(id: Int): Todo?

    suspend fun add(todo: Todo)
    suspend fun update(todo: Todo)
    suspend fun delete(todo: Todo)

}
