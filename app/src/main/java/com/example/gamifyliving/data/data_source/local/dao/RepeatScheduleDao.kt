package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.table.RepeatScheduleEntity

@Dao
interface RepeatScheduleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(repeatScheduleEntity: RepeatScheduleEntity)

    @Update
    suspend fun update(repeatScheduleEntity: RepeatScheduleEntity)

    @Delete
    suspend fun delete(repeatScheduleEntity: RepeatScheduleEntity)

    @Query("SELECT * FROM repeat_schedule WHERE habit_id = :habitId")
    suspend fun getByHabitId(habitId: Int): RepeatScheduleEntity?

    @Query("DELETE FROM repeat_schedule WHERE habit_id = :habitId")
    suspend fun delete(habitId: Int)

}