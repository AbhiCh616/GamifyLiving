package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamifyliving.data.data_source.local.model.RewardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RewardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rewards: List<RewardEntity>)

    @Query("DELETE FROM reward WHERE task_id = :taskId")
    suspend fun deleteRewardsForTask(taskId: Int)

    @Query("SELECT * FROM reward")
    fun getAll(): Flow<List<RewardEntity>>

}
