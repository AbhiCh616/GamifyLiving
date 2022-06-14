package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.domain.entity.Reward
import com.example.gamifyliving.domain.entity.Task
import com.example.gamifyliving.application.repository.RewardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRewardsForTask @Inject constructor(
    private val repository: RewardRepository
) {
    operator fun invoke(task: Task): Flow<List<Reward>> = repository.getRewardsForTask(task.id)
}
