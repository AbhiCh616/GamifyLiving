package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.example.gamifyliving.data.data_source.local.model.table.DateScheduleEntity

@Dao
interface DateScheduleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dateScheduleEntity: DateScheduleEntity)

    @Update
    suspend fun update(dateScheduleEntity: DateScheduleEntity)

}
