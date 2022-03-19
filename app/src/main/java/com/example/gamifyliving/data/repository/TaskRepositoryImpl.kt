package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.TaskDao
import com.example.gamifyliving.data.data_source.local.mapper.toDataModel
import com.example.gamifyliving.data.data_source.local.mapper.toDomainModel
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun addTask(task: Task) {
        taskDao.insert(task.toDataModel())
    }

    override suspend fun updateTask(task: Task) {
        taskDao.update(task.toDataModel())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.delete(task.toDataModel())
    }

    override suspend fun getTaskById(id: Int): Task? = taskDao.getTaskById(id)?.toDomainModel()

    override fun observeTasks(): Flow<List<Task>> = taskDao.getAll().map {
        it.toDomainModel()
    }

}
