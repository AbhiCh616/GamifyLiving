package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.DateScheduleEntity

@Dao
interface TodoScheduleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(dateScheduleEntity: DateScheduleEntity)

    @Update
    suspend fun update(dateScheduleEntity: DateScheduleEntity)

    @Delete
    suspend fun delete(dateScheduleEntity: DateScheduleEntity)

    @Query("SELECT * FROM date_schedule WHERE todo_id = :todoId")
    suspend fun get(todoId: Int): DateScheduleEntity?

    @Query("DELETE FROM date_schedule WHERE todo_id = :todoId")
    suspend fun delete(todoId: Int)

}
