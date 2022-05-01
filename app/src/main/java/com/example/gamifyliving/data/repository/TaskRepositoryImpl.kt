package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.TaskDao
import com.example.gamifyliving.data.data_source.local.mapper.toDomainModel
import com.example.gamifyliving.data.data_source.local.model.*
import com.example.gamifyliving.domain.model.*
import com.example.gamifyliving.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun addTask(task: Task) {
        val taskEntity = TaskEntity(
            name = task.name,
            status = task.status
        )
        val taskId = taskDao.insert(taskEntity)
        if (task is Todo) {
            val todoEntity = TodoEntity(
                coinsReward = task.coinsReward,
                taskId = taskId.toInt()
            )
            val todoId = taskDao.insert(todoEntity)
            if (task.schedule is DateSchedule) {
                val todoScheduleEntity = TodoScheduleEntity(
                    scheduledDate = task.schedule.date,
                    timeSpan =
                    if (task.schedule.timeSpan != null) {
                        TimeSpanEntity(
                            startTime = task.schedule.timeSpan.startTime,
                            endTime = task.schedule.timeSpan.endTime
                        )
                    } else {
                        null
                    },
                    todoId = todoId.toInt()
                )
            }
        }
        if (task is Habit) {
            val habitEntity = HabitEntity(
                taskId = taskId.toInt()
            )
            val habitId = taskDao.insert(habitEntity)
            if (task.schedule is EverydaySchedule) {
                val everydayScheduleEntity = EverydayScheduleEntity(
                    timeSpan = if ((task.schedule as EverydaySchedule).timeSpan != null) {
                        TimeSpanEntity(
                            startTime = (task.schedule as EverydaySchedule).timeSpan!!.startTime,
                            endTime = (task.schedule as EverydaySchedule).timeSpan!!.endTime,
                        )
                    } else {
                        null
                    },
                    habitId = habitId.toInt()
                )
            }
            if (task.schedule is RepeatAfterSchedule) {
                val repeatAfterScheduleEntity = RepeatAfterScheduleEntity(
                    startDate = (task.schedule as RepeatAfterSchedule).startDate,
                    repeatAfter = (task.schedule as RepeatAfterSchedule).interval,
                    timeSpan = if ((task.schedule as EverydaySchedule).timeSpan != null) {
                        TimeSpanEntity(
                            startTime = (task.schedule as EverydaySchedule).timeSpan!!.startTime,
                            endTime = (task.schedule as EverydaySchedule).timeSpan!!.endTime,
                        )
                    } else {
                        null
                    },
                    habitId = habitId.toInt()
                )
            }
            if (task.schedule is WeekDaySchedule) {
                val weekDayScheduleEntity = WeekDayScheduleEntity(
                    sunday = (task.schedule as WeekDaySchedule).sunday,
                    monday = (task.schedule as WeekDaySchedule).monday,
                    tuesday = (task.schedule as WeekDaySchedule).tuesday,
                    wednesday = (task.schedule as WeekDaySchedule).wednesday,
                    thursday = (task.schedule as WeekDaySchedule).thursday,
                    friday = (task.schedule as WeekDaySchedule).friday,
                    saturday = (task.schedule as WeekDaySchedule).saturday,
                    timeSpan = if ((task.schedule as EverydaySchedule).timeSpan != null) {
                        TimeSpanEntity(
                            startTime = (task.schedule as EverydaySchedule).timeSpan!!.startTime,
                            endTime = (task.schedule as EverydaySchedule).timeSpan!!.endTime,
                        )
                    } else {
                        null
                    },
                    habitId = habitId.toInt()
                )
            }
        }
    }

    override suspend fun updateTask(task: Task) {
        taskDao.update(task.toDataModel())
    }

    override suspend fun deleteTask(task: Task) {
        taskDao.delete(task.toDataModel())
    }

    override suspend fun getTaskById(id: Int): Task? =
        taskDao.getTaskWithDetails(id)?.toDomainModel()

    override fun observeTasks(): Flow<List<Task>> = taskDao.getAll().map {
        it.toDomainModel()
    }

}
