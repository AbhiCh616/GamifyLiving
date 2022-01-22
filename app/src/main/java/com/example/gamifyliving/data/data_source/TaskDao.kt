package com.example.gamifyliving.data.data_source

import androidx.room.*
import com.example.gamifyliving.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task WHERE uid = :id")
    suspend fun getTaskById(id : Int): Task?

    @Query("SELECT * FROM task")
    fun getAll(): Flow<List<Task>>

}
