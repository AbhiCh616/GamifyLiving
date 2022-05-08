package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.HabitEntity

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(habitEntity: HabitEntity): Long

    @Update
    suspend fun update(habitEntity: HabitEntity): Int

    @Delete
    suspend fun delete(habitEntity: HabitEntity)

    @Query("SELECT * FROM habit WHERE task_id = :taskId")
    suspend fun get(taskId: Int): HabitEntity?

    @Query("SELECT id FROM habit WHERE task_id = :taskId")
    suspend fun getId(taskId: Int): Int?

}
