package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.RewardRepository
import com.example.gamifyliving.domain.repository.TaskRepository
import javax.inject.Inject

class AddTask @Inject constructor(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository
) {
    suspend operator fun invoke(task: Task, rewards: List<Reward>? = null) {
        taskRepository.addTask(task)
        if (rewards != null) {
            rewardRepository.addRewards(rewards)
        }
    }
}
