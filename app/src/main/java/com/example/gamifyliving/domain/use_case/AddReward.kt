package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.repository.RewardRepository
import javax.inject.Inject

class AddReward @Inject constructor(
    private val repository: RewardRepository
) {
    suspend operator fun invoke(reward: Reward) {
        repository.addReward(reward)
    }
}
