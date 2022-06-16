package com.example.gamifyliving.data.repository

import com.example.gamifyliving.domain.repository.TaskRepository
import com.example.gamifyliving.data.data_source.local.dao.*
import com.example.gamifyliving.data.data_source.local.mapper.*
import com.example.gamifyliving.domain.model.entity.*
import com.example.gamifyliving.domain.model.value_object.EverydaySchedule
import com.example.gamifyliving.domain.model.value_object.RepeatAfterSchedule
import com.example.gamifyliving.domain.model.value_object.WeekDaySchedule
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val todoDao: TodoDao,
    private val habitDao: HabitDao,
    private val rewardDao: RewardDao,
    private val dateScheduleDao: DateScheduleDao,
    private val everydayScheduleDao: EverydayScheduleDao,
    private val repeatScheduleDao: RepeatScheduleDao,
    private val weekDayScheduleDao: WeekDayScheduleDao,
) : TaskRepository {

    override suspend fun addTask(task: Task): Int {
        // Add Task
        val taskEntity = task.toTaskEntity()
        val taskId = taskDao.insert(taskEntity = taskEntity).toInt()



        return taskId
    }

    override suspend fun updateTask(task: Task) {
        // Update Task
        val taskEntity = task.toTaskEntity()
        val taskId = taskEntity.id
        taskDao.update(taskEntity = taskEntity)



    }

    override suspend fun deleteTask(task: Task) {
        // Other relevant entities have direct or transitive dependency
        taskDao.delete(task.toTaskEntity())
    }

    override suspend fun getTaskById(id: Int): Task? =
        taskDao.getTaskWithDetails(id)?.toDomainModel()

    override fun observeTasks(): Flow<List<Task>> = taskDao.getAll().map {
        it.toDomainModel()
    }

}
