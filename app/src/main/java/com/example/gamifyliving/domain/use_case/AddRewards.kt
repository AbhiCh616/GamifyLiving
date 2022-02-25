package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.repository.RewardRepository
import javax.inject.Inject

class AddRewards @Inject constructor(
    private val repository: RewardRepository
) {
    suspend operator fun invoke(rewards: List<Reward>) {
        repository.addRewards(rewards)
    }
}
