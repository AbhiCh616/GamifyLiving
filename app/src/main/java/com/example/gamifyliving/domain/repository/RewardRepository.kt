package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface RewardRepository {

    suspend fun addRewards(rewards: List<Reward>)

    suspend fun deleteRewardsForTask(taskId: Int)

    fun getRewardsForTask(taskId: Int): Flow<List<Reward>>

}
