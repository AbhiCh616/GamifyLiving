package com.example.gamifyliving.application.repository

import com.example.gamifyliving.domain.entity.Reward
import kotlinx.coroutines.flow.Flow

interface RewardRepository {

    suspend fun addRewards(rewards: List<Reward>)

    suspend fun deleteRewardsForTask(taskId: Int)

    fun getRewardsForTask(taskId: Int): Flow<List<Reward>>

}
