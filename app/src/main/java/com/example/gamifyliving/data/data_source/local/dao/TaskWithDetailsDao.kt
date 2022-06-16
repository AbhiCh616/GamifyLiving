package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.gamifyliving.data.data_source.local.model.read_model.TaskWithDetailsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskWithDetailsDao {

    @Transaction
    @Query("SELECT * FROM task WHERE id = :id")
    suspend fun getById(id: Int): TaskWithDetailsEntity?

    @Transaction
    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<TaskWithDetailsEntity>>

}
