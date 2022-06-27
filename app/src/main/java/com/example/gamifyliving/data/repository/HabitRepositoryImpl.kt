package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.*
import com.example.gamifyliving.data.data_source.local.mapper.*
import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.value_object.EverydaySchedule
import com.example.gamifyliving.domain.model.value_object.RepeatSchedule
import com.example.gamifyliving.domain.model.value_object.WeekDaySchedule
import com.example.gamifyliving.domain.repository.HabitRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val taskWithDetailsDao: TaskWithDetailsDao,
    private val habitDao: HabitDao,
    private val everydayScheduleDao: EverydayScheduleDao,
    private val repeatScheduleDao: RepeatScheduleDao,
    private val weekDayScheduleDao: WeekDayScheduleDao,
    private val rewardDao: RewardDao
) : HabitRepository {

    override fun observe() = taskWithDetailsDao.getAll().map {
        it.toHabitList()
    }

    override suspend fun getById(id: Int) =
        taskWithDetailsDao.getById(id = id)?.let { taskWithDetails ->
            if (taskWithDetails.habitWithSchedule != null) {
                taskWithDetails.toHabit()
            } else null
        }

    override suspend fun add(habit: Habit) {

        val taskEntity = habit.toTaskEntity()
        val taskId = taskDao.insert(taskEntity = taskEntity).toInt()

        val habitEntity = habit.toHabitEntity(taskId = taskId)
        habitDao.insert(habitEntity = habitEntity)

        when (habit.schedule) {
            is EverydaySchedule -> {
                val everydayScheduleEntity =
                    habit.schedule.toEverydayScheduleEntity(habitId = taskId)
                everydayScheduleDao.insert(everydayScheduleEntity = everydayScheduleEntity)
            }
            is RepeatSchedule -> {
                val repeatAfterScheduleEntity =
                    habit.schedule.toRepeatScheduleEntity(habitId = taskId)
                repeatScheduleDao.insert(repeatScheduleEntity = repeatAfterScheduleEntity)
            }
            is WeekDaySchedule -> {
                val weekDayScheduleEntity =
                    habit.schedule.toWeekDayScheduleEntity(habitId = taskId)
                weekDayScheduleDao.insert(weekDayScheduleEntity = weekDayScheduleEntity)
            }
        }

        habit.rewards?.let { rewards ->
            val rewardsEntity = rewards.toRewardEntityList(taskId = taskId)
            rewardDao.insert(rewardsEntity)
        }

    }

    override suspend fun update(habit: Habit) {

        val taskEntity = habit.toTaskEntity()
        taskDao.update(taskEntity = taskEntity)

        val habitEntity = habit.toHabitEntity()
        habitDao.update(habitEntity = habitEntity)

        everydayScheduleDao.deleteByHabitId(habitId = habit.id)
        repeatScheduleDao.deleteByHabitId(habitId = habit.id)
        weekDayScheduleDao.deleteByHabitId(habitId = habit.id)
        when (habit.schedule) {
            is EverydaySchedule -> {
                val everydayScheduleEntity =
                    habit.schedule.toEverydayScheduleEntity(habitId = habit.id)
                everydayScheduleDao.insert(everydayScheduleEntity = everydayScheduleEntity)
            }
            is RepeatSchedule -> {
                val repeatAfterScheduleEntity =
                    habit.schedule.toRepeatScheduleEntity(habitId = habit.id)
                repeatScheduleDao.insert(repeatScheduleEntity = repeatAfterScheduleEntity)
            }
            is WeekDaySchedule -> {
                val weekDayScheduleEntity =
                    habit.schedule.toWeekDayScheduleEntity(habitId = habit.id)
                weekDayScheduleDao.insert(weekDayScheduleEntity = weekDayScheduleEntity)
            }
        }

        rewardDao.deleteByTaskId(taskId = habit.id)
        habit.rewards?.let { rewards ->
            val rewardsEntity = rewards.toRewardEntityList(taskId = habit.id)
            rewardDao.insert(rewardsEntity)
        }

    }

    override suspend fun delete(habit: Habit) {

        val taskEntity = habit.toTaskEntity()
        taskDao.delete(taskEntity = taskEntity)

    }

}
