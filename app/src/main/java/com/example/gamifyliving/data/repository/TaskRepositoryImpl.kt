package com.example.gamifyliving.data.repository

import com.example.gamifyliving.application.repository.TaskRepository
import com.example.gamifyliving.data.data_source.local.dao.*
import com.example.gamifyliving.data.data_source.local.mapper.*
import com.example.gamifyliving.domain.entity.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val todoDao: TodoDao,
    private val habitDao: HabitDao,
    private val rewardDao: RewardDao,
    private val todoScheduleDao: TodoScheduleDao,
    private val everydayScheduleDao: EverydayScheduleDao,
    private val repeatScheduleDao: RepeatScheduleDao,
    private val weekDayScheduleDao: WeekDayScheduleDao,
) : TaskRepository {

    override suspend fun addTask(task: Task): Int {
        // Add Task
        val taskEntity = task.toTaskEntity()
        val taskId = taskDao.insert(taskEntity = taskEntity).toInt()

        when (task) {
            is Todo -> {
                // Add Todo_Entity
                val todoEntity = task.toTodoEntity(taskId = taskId)
                val todoId = todoDao.insert(todoEntity = todoEntity).toInt()

                task.schedule?.let { dateSchedule ->
                    //Add Todo_Schedule
                    val todoScheduleEntity = dateSchedule.toTodoScheduleEntity(todoId = todoId)
                    todoScheduleDao.insert(todoScheduleEntity = todoScheduleEntity)
                }

                if (task.rewards != null) {
                    rewardDao.insert(task.toRewardDataModel())
                }
            }

            is Habit -> {
                // Add Habit
                val habitEntity = task.toHabitEntity(taskId = taskId)
                val habitId = habitDao.insert(habitEntity = habitEntity).toInt()

                task.schedule?.let { schedule ->
                    // Add Schedule
                    when (schedule) {
                        is EverydaySchedule -> {
                            // Add Everyday_Schedule
                            val everydayScheduleEntity =
                                schedule.toEverydayScheduleEntity(habitId = habitId)
                            everydayScheduleDao.insert(everydayScheduleEntity = everydayScheduleEntity)
                        }
                        is RepeatAfterSchedule -> {
                            // Add Repeat_Schedule
                            val repeatScheduleEntity =
                                schedule.toRepeatAfterScheduleEntity(habitId = habitId)
                            repeatScheduleDao.insert(repeatScheduleEntity = repeatScheduleEntity)
                        }
                        is WeekDaySchedule -> {
                            // Add Week_Day_Schedule
                            val weekDayScheduleEntity =
                                schedule.toWeekDayScheduleEntity(habitId = habitId)
                            weekDayScheduleDao.insert(weekDayScheduleEntity = weekDayScheduleEntity)
                        }
                    }

                    if (task.rewards != null) {
                        rewardDao.insert(task.toRewardDataModel())
                    }
                }
            }
        }

        return taskId
    }

    override suspend fun updateTask(task: Task) {
        // Update Task
        val taskEntity = task.toTaskEntity()
        val taskId = taskEntity.id
        taskDao.update(taskEntity = taskEntity)

        when (task) {
            is Todo -> {
                // Update Todo_Entity
                val todoId = todoDao.getId(taskId = taskId)!!
                val todoEntity = task.toTodoEntity(taskId = taskId, todoId = todoId)
                todoDao.update(todoEntity = todoEntity)

                /*task.schedule?.let { dateSchedule ->
                    // Update Todo_Schedule
                    val todoScheduleId = todoScheduleDao.getId(todoId = todoId)!!
                    val todoScheduleEntity = dateSchedule.toTodoScheduleEntity(
                        todoId = todoId,
                        todoScheduleId = 1
                    )
                    todoScheduleDao.update(todoScheduleEntity = todoScheduleEntity)
                }*/

                // Delete schedule
                todoScheduleDao.delete(todoId = todoId)

                task.schedule?.let { dateSchedule ->
                    //Add Todo_Schedule
                    val todoScheduleEntity = dateSchedule.toTodoScheduleEntity(todoId = todoId)
                    todoScheduleDao.insert(todoScheduleEntity = todoScheduleEntity)
                }

                rewardDao.deleteRewardsForTask(task.id)
                if (task.rewards != null) {
                    rewardDao.insert(task.toRewardDataModel())
                }
            }

            is Habit -> {
                // Update Habit
                val habitId = habitDao.getId(taskId = taskId)!!
                val habitEntity = task.toHabitEntity(taskId = taskId, habitId = habitId)
                habitDao.update(habitEntity = habitEntity)

                // Delete all schedules
                everydayScheduleDao.delete(habitId = habitId)
                repeatScheduleDao.delete(habitId = habitId)
                weekDayScheduleDao.delete(habitId = habitId)

                task.schedule?.let { schedule ->
                    // Add Schedule
                    when (schedule) {
                        is EverydaySchedule -> {
                            // Add Everyday_Schedule
                            val everydayScheduleEntity =
                                schedule.toEverydayScheduleEntity(habitId = habitId)
                            everydayScheduleDao.insert(everydayScheduleEntity = everydayScheduleEntity)
                        }
                        is RepeatAfterSchedule -> {
                            // Add Repeat_Schedule
                            val repeatScheduleEntity =
                                schedule.toRepeatAfterScheduleEntity(habitId = habitId)
                            repeatScheduleDao.insert(repeatScheduleEntity = repeatScheduleEntity)
                        }
                        is WeekDaySchedule -> {
                            // Add Week_Day_Schedule
                            val weekDayScheduleEntity =
                                schedule.toWeekDayScheduleEntity(habitId = habitId)
                            weekDayScheduleDao.insert(weekDayScheduleEntity = weekDayScheduleEntity)
                        }
                    }
                }

                rewardDao.deleteRewardsForTask(task.id)
                if (task.rewards != null) {
                    rewardDao.insert(task.toRewardDataModel())
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
