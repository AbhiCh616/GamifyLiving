package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.gamifyliving.data.data_source.local.model.table.HabitEntity

@Dao
interface HabitDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(habitEntity: HabitEntity)

    @Update
    suspend fun update(habitEntity: HabitEntity)

}
