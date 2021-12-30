package com.example.gamifyliving.data.database

import androidx.room.*
import com.example.gamifyliving.data.model.Stat
import kotlinx.coroutines.flow.Flow

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stat: Stat)

    @Update
    suspend fun update(stat: Stat)

    @Delete
    suspend fun delete(stat: Stat)

    @Query("SELECT * FROM stat WHERE name = :name")
    suspend fun get(name: String): Stat

    @Query("SELECT * FROM stat ORDER BY value, name ASC")
    fun getAll(): Flow<List<Stat>>

}