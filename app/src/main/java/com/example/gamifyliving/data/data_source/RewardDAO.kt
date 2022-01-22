package com.example.gamifyliving.data.data_source

import androidx.room.*
import com.example.gamifyliving.domain.model.Reward

@Dao
interface RewardDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(reward: Reward)

    @Update
    suspend fun update(reward: Reward)

    @Delete
    suspend fun delete(reward: Reward)

}
