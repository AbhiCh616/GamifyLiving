package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.repository.RewardRepository

class AddReward(
    private val repository: RewardRepository
) {
    suspend operator fun invoke(reward: Reward) {
        repository.addReward(reward)
    }
}
