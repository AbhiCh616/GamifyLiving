package com.example.gamifyliving.data.data_source.local

import androidx.room.*
import com.example.gamifyliving.domain.model.Stat
import kotlinx.coroutines.flow.Flow

@Dao
interface StatDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(stat: Stat)

    @Update
    suspend fun update(stat: Stat)

    @Delete
    suspend fun delete(stat: Stat)

    @Query("SELECT * FROM stat WHERE uid = :id")
    suspend fun getStatById(id: Int): Stat?

    @Query("SELECT * FROM stat")
    fun getAll(): Flow<List<Stat>>

}
