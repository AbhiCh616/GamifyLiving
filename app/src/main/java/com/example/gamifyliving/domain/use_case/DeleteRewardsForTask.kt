package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.repository.RewardRepository
import javax.inject.Inject

class DeleteRewardsForTask @Inject constructor(
    private val repository: RewardRepository
) {
    suspend operator fun invoke(taskId: Int) {
        repository.deleteRewardsForTask(taskId)
    }
}
