package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.table.StatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stat: StatEntity)

    @Update
    suspend fun update(stat: StatEntity)

    @Delete
    suspend fun delete(stat: StatEntity)

    @Query("SELECT * FROM stat WHERE id = :id")
    suspend fun getStatById(id: Int): StatEntity?

    @Query("SELECT * FROM stat")
    fun getAll(): Flow<List<StatEntity>>

}
