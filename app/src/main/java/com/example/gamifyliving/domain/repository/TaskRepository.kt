package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    suspend fun addTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

    suspend fun getTaskById(id: Int): Task?

    fun observeTasks(): Flow<List<Task>>

}
