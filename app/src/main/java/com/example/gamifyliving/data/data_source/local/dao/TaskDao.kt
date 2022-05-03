package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: TaskEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: TodoEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoSchedule: TodoScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(habit: HabitEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(everydaySchedule: EverydayScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(repeatAfterSchedule: RepeatAfterScheduleEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weekDaySchedule: WeekDayScheduleEntity)

    @Update
    suspend fun update(task: TaskEntity): Int

    @Update
    suspend fun update(todo: TodoEntity): Int

    @Update
    suspend fun update(todoSchedule: TodoScheduleEntity)

    @Update
    suspend fun update(habit: HabitEntity): Int

    @Update
    suspend fun update(everydaySchedule: EverydayScheduleEntity)

    @Update
    suspend fun update(repeatAfterSchedule: RepeatAfterScheduleEntity)

    @Update
    suspend fun update(weekDaySchedule: WeekDayScheduleEntity)

    @Delete
    suspend fun delete(task: TaskEntity)

    @Delete
    suspend fun delete(todo: TodoEntity)

    @Delete
    suspend fun delete(todoSchedule: TodoScheduleEntity)

    @Delete
    suspend fun delete(habit: HabitEntity)

    @Delete
    suspend fun delete(everydaySchedule: EverydayScheduleEntity)

    @Delete
    suspend fun delete(repeatAfterSchedule: RepeatAfterScheduleEntity)

    @Delete
    suspend fun delete(weekDaySchedule: WeekDayScheduleEntity)

    @Transaction
    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskWithDetails(id: Int): TaskWithDetailsEntity?

    @Transaction
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<TaskWithDetailsEntity>>

}
