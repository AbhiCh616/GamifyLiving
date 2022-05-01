package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.RewardRepository
import com.example.gamifyliving.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTask @Inject constructor(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository
) {
    suspend operator fun invoke(task: Task, rewards: List<Reward>) {
        // Update task
        taskRepository.updateTask(task)

        // Update rewards for task
        rewardRepository.deleteRewardsForTask(taskId = task.id)
        rewardRepository.addRewards(rewards)
    }
}
