package com.example.gamifyliving.data.data_source

import androidx.room.*
import com.example.gamifyliving.domain.model.Reward
import kotlinx.coroutines.flow.Flow

@Dao
interface RewardDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reward: Reward)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rewards: List<Reward>)

    @Update
    suspend fun update(reward: Reward)

    @Delete
    suspend fun delete(reward: Reward)

    @Query("DELETE FROM reward WHERE taskId = :taskId")
    suspend fun deleteRewardsForTask(taskId: Int)

    @Query("SELECT * FROM reward")
    fun getAll(): Flow<List<Reward>>

}
