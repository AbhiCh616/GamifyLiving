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
    suspend fun update(task: TaskEntity): Long

    @Update
    suspend fun update(todo: TodoEntity): Long

    @Update
    suspend fun update(todoSchedule: TodoScheduleEntity)

    @Update
    suspend fun update(habit: HabitEntity): Long

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

    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskWithDetails(id: Int): TaskWithDetailsEntity?

    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<TaskWithDetailsEntity>>

}
