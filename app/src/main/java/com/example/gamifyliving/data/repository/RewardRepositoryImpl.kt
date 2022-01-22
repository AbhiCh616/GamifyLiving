package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.RewardDAO
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.repository.RewardRepository

class RewardRepositoryImpl(
    private val rewardDAO: RewardDAO
) : RewardRepository {

    override suspend fun addReward(reward: Reward) {
        rewardDAO.insert(reward)
    }

    override suspend fun updateReward(reward: Reward) {
        rewardDAO.update(reward)
    }

    override suspend fun deleteReward(reward: Reward) {
        rewardDAO.delete(reward)
    }

}

