package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.database.TaskDao
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.delete(task)
    }

    override fun observeTasks(): Flow<List<Task>> = taskDao.getAll()

}
