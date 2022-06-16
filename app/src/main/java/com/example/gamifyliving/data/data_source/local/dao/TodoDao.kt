package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.TodoEntity

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todoEntity: TodoEntity): Long

    @Update
    suspend fun update(todoEntity: TodoEntity): Int

    @Delete
    suspend fun delete(todoEntity: TodoEntity)

    @Query("SELECT * FROM todo WHERE task_id = :taskId")
    suspend fun get(taskId: Int): TodoEntity?

    @Query("SELECT task_id FROM todo WHERE task_id = :taskId")
    suspend fun getId(taskId: Int): Int?

}
