package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.Reward

interface RewardRepository {

    suspend fun addReward(reward: Reward)

    suspend fun updateReward(reward: Reward)

    suspend fun deleteReward(reward: Reward)

}
