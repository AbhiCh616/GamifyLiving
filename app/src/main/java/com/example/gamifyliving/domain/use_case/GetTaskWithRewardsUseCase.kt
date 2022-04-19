package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.TaskWithRewards
import com.example.gamifyliving.domain.repository.RewardRepository
import com.example.gamifyliving.domain.repository.TaskRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetTaskWithRewardsUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository
) {

    suspend operator fun invoke(taskId: Int) = runSuspendCatching {
        val task = taskRepository.getTaskById(id = taskId)
        val rewards = rewardRepository.getRewardsForTask(taskId = taskId).first()

        TaskWithRewards(
            task = task!!,
            rewards = rewards
        )
    }

}
