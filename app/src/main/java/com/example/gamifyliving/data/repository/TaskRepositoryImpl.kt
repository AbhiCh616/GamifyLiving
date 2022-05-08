package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.TaskDao
import com.example.gamifyliving.data.data_source.local.mapper.*
import com.example.gamifyliving.domain.model.*
import com.example.gamifyliving.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun addTask(task: Task) {
        val taskEntity = task.toTaskEntity()
        val taskId = taskDao.insert(taskEntity).toInt()
        when (task) {
            is Todo -> {
                val todoEntity = task.toTodoEntity(taskId = taskId)
                val todoId = taskDao.insert(todoEntity).toInt()
                task.schedule?.let { schedule ->
                    val todoScheduleEntity = schedule.toTodoScheduleEntity(todoId = todoId)
                    taskDao.insert(todoScheduleEntity)
                }
            }
            is Habit -> {
                val habitEntity = task.toHabitEntity(taskId = taskId)
                val habitId = taskDao.insert(habitEntity).toInt()
                task.schedule?.let { schedule ->
                    when (schedule) {
                        is EverydaySchedule -> {
                            val everydayScheduleEntity =
                                schedule.toEverydayScheduleEntity(habitId = habitId)
                            taskDao.insert(everydayScheduleEntity)
                        }
                        is RepeatAfterSchedule -> {
                            val repeatAfterScheduleEntity =
                                schedule.toRepeatAfterScheduleEntity(habitId = habitId)
                            taskDao.insert(repeatAfterScheduleEntity)
                        }
                        is WeekDaySchedule -> {
                            val weekDayScheduleEntity =
                                schedule.toWeekDayScheduleEntity(habitId = habitId)
                            taskDao.insert(weekDayScheduleEntity)
                        }
                    }
                }
            }
        }
    }

    override suspend fun updateTask(task: Task) {
        val taskEntity = task.toTaskEntity()
        val taskId = taskDao.update(taskEntity)
        when (task) {
            is Todo -> {
                val todoEntity = task.toTodoEntity(taskId = taskId)
                val todoId = taskDao.update(todoEntity)
                task.schedule?.let { schedule ->
                    val todoScheduleEntity = schedule.toTodoScheduleEntity(todoId = todoId)
                    taskDao.update(todoScheduleEntity)
                }
            }
            is Habit -> {
                val habitEntity = task.toHabitEntity(taskId = taskId)
                val habitId = taskDao.update(habitEntity)
                task.schedule?.let { schedule ->
                    when (schedule) {
                        is EverydaySchedule -> {
                            val everydayScheduleEntity =
                                schedule.toEverydayScheduleEntity(habitId = habitId)
                            taskDao.update(everydayScheduleEntity)
                        }
                        is RepeatAfterSchedule -> {
                            val repeatAfterScheduleEntity =
                                schedule.toRepeatAfterScheduleEntity(habitId = habitId)
                            taskDao.update(repeatAfterScheduleEntity)
                        }
                        is WeekDaySchedule -> {
                            val weekDayScheduleEntity =
                                schedule.toWeekDayScheduleEntity(habitId = habitId)
                            taskDao.update(weekDayScheduleEntity)
                        }
                    }
                }
            }
        }
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
