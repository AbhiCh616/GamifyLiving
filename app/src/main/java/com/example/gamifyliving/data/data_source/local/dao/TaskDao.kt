package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.TaskEntity
import com.example.gamifyliving.data.data_source.local.model.TaskWithDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(taskEntity: TaskEntity): Long

    @Update
    suspend fun update(taskEntity: TaskEntity): Int

    @Delete
    suspend fun delete(taskEntity: TaskEntity)

    @Transaction
    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getTaskWithDetails(id: Int): TaskWithDetailsEntity?

    @Transaction
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<TaskWithDetailsEntity>>

}
