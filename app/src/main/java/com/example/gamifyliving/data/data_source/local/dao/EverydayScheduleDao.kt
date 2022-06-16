package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.table.EverydayScheduleEntity

@Dao
interface EverydayScheduleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(everydayScheduleEntity: EverydayScheduleEntity)

    @Update
    suspend fun update(everydayScheduleEntity: EverydayScheduleEntity)

    @Delete
    suspend fun delete(everydayScheduleEntity: EverydayScheduleEntity)

    @Query("SELECT * FROM everyday_schedule WHERE habit_id = :habitId")
    suspend fun get(habitId: Int): EverydayScheduleEntity?

    @Query("DELETE FROM everyday_schedule WHERE habit_id = :habitId")
    suspend fun delete(habitId: Int)
}
