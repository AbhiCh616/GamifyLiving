package com.example.gamifyliving.repository

import com.example.gamifyliving.data.database.RewardDAO
import com.example.gamifyliving.data.model.Reward

class RewardRepository(
    private val rewardDAO: RewardDAO
) {

    suspend fun addReward(reward: Reward) {
        rewardDAO.insert(reward)
    }

    suspend fun updateReward(reward: Reward) {
        rewardDAO.update(reward)
    }

    suspend fun deleteReward(reward: Reward) {
        rewardDAO.delete(reward)
    }

}
