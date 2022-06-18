package com.example.gamifyliving.data.data_source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.gamifyliving.data.data_source.local.model.table.RewardEntity

@Dao
interface RewardDao {

    @Query("DELETE FROM reward WHERE task_id = :taskId")
    suspend fun deleteByTaskId(taskId: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(rewards: List<RewardEntity>)

}
