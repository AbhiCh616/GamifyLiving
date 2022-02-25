package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface RewardRepository {

    suspend fun addReward(reward: Reward)

    suspend fun addRewards(rewards: List<Reward>)

    suspend fun updateReward(reward: Reward)

    suspend fun deleteReward(reward: Reward)

    fun getRewardsForTask(task: Task): Flow<List<Reward>>

}
