package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamifyliving.data.data_source.local.model.table.EverydayScheduleEntity

@Dao
interface EverydayScheduleDao {

    @Query("DELETE FROM everyday_schedule WHERE habit_id = :habitId")
    suspend fun deleteByHabitId(habitId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(everydayScheduleEntity: EverydayScheduleEntity)

}
