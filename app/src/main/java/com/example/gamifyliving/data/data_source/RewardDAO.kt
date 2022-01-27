package com.example.gamifyliving.data.data_source

import androidx.room.*
import com.example.gamifyliving.domain.model.Reward
import kotlinx.coroutines.flow.Flow

@Dao
interface RewardDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reward: Reward)

    @Update
    suspend fun update(reward: Reward)

    @Delete
    suspend fun delete(reward: Reward)

    @Query("SELECT * FROM reward")
    fun getAll(): Flow<List<Reward>>

}
