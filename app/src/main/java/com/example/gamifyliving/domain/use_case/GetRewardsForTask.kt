package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.RewardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRewardsForTask @Inject constructor(
    private val repository: RewardRepository
) {
    operator fun invoke(task: Task): Flow<List<Reward>> = repository.getRewardsForTask(task.id)
}
