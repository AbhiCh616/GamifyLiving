package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.WeekDayScheduleEntity

@Dao
interface WeekDayScheduleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weekDayScheduleEntity: WeekDayScheduleEntity)

    @Update
    suspend fun update(weekDayScheduleEntity: WeekDayScheduleEntity)

    @Delete
    suspend fun delete(weekDayScheduleEntity: WeekDayScheduleEntity)

    @Query("SELECT * FROM week_day_schedule WHERE habit_id = :habitId")
    suspend fun getByHabitId(habitId: Int): WeekDayScheduleEntity?

    @Query("DELETE FROM week_day_schedule WHERE habit_id = :habitId")
    suspend fun delete(habitId: Int)

}
