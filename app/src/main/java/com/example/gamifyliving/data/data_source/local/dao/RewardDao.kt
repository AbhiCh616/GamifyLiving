package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.*
import com.example.gamifyliving.domain.model.Reward
import kotlinx.coroutines.flow.Flow

@Dao
interface RewardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rewards: List<Reward>)

    @Query("DELETE FROM reward WHERE taskId = :taskId")
    suspend fun deleteRewardsForTask(taskId: Int)

    @Query("SELECT * FROM reward")
    fun getAll(): Flow<List<Reward>>

}
