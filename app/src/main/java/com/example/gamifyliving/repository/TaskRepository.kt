package com.example.gamifyliving.repository

import com.example.gamifyliving.data.database.TaskDao
import com.example.gamifyliving.domain.model.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(
    private val taskDao: TaskDao
) {

    suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }

    fun observeTasks(): Flow<List<Task>> = taskDao.getAll()

}
