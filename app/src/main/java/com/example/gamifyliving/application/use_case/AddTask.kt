package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.application.repository.RewardRepository
import com.example.gamifyliving.application.repository.TaskRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddTask @Inject constructor(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository
) {
    suspend operator fun invoke(task: Task, rewards: List<Reward>? = null) = runSuspendCatching {
        withContext(NonCancellable) {
            val taskId = taskRepository.addTask(task)
            if (rewards != null) {
                val updatedRewards = rewards.map { reward ->
                    reward.copy(taskId = taskId)
                }
                rewardRepository.addRewards(updatedRewards)
            }
        }
    }
}
