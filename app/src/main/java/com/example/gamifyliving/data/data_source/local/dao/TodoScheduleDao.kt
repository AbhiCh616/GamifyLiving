package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.TodoScheduleEntity

@Dao
interface TodoScheduleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoScheduleEntity: TodoScheduleEntity)

    @Update
    suspend fun update(todoScheduleEntity: TodoScheduleEntity)

    @Delete
    suspend fun delete(todoScheduleEntity: TodoScheduleEntity)

    @Query("SELECT * FROM todo_schedule WHERE todo_id = :todoId")
    suspend fun get(todoId: Int): TodoScheduleEntity?

    @Query("DELETE FROM todo_schedule WHERE todo_id = :todoId")
    suspend fun delete(todoId: Int)

}
