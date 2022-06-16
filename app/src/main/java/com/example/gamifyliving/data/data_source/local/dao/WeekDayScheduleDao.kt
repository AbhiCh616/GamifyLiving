package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamifyliving.data.data_source.local.model.table.WeekDayScheduleEntity

@Dao
interface WeekDayScheduleDao {

    @Query("DELETE FROM week_day_schedule WHERE habit_id = :habitId")
    suspend fun delete(habitId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(weekDayScheduleEntity: WeekDayScheduleEntity)

}
