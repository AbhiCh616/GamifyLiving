package com.example.gamifyliving.data.database

import androidx.room.*
import com.example.gamifyliving.data.model.Reward
import kotlinx.coroutines.flow.Flow

@Dao
interface RewardDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reward: Reward)

    @Update
    suspend fun update(reward: Reward)

    @Delete
    suspend fun delete(reward: Reward)

    /*@Query("SELECT * FROM reward where taskId = :taskId")
    fun getRewardsForTask(taskId: Int): Flow<List<Reward>>*/

}
