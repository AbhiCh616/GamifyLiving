package com.example.gamifyliving.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamifyliving.data.model.Stat
import kotlinx.coroutines.flow.Flow

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stat: Stat)

    @Query("SELECT * FROM stat ORDER BY value, name ASC")
    fun getAll(): Flow<List<Stat>>

}