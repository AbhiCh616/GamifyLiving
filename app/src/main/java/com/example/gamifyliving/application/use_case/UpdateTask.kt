package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.domain.entity.Reward
import com.example.gamifyliving.domain.entity.Task
import com.example.gamifyliving.application.repository.RewardRepository
import com.example.gamifyliving.application.repository.TaskRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateTask @Inject constructor(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository
) {
    suspend operator fun invoke(task: Task, rewards: List<Reward>) = runSuspendCatching {
        withContext(NonCancellable) {
            // Update task
            taskRepository.updateTask(task)

            // Update rewards for task
            rewardRepository.deleteRewardsForTask(taskId = task.id)
            rewardRepository.addRewards(rewards)
        }
    }
}
