package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.RewardRepository
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.repository.TaskRepository
import com.example.gamifyliving.domain.util.DataStoreManager
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ChangeTaskStatus @Inject constructor(
    private val taskRepository: TaskRepository,
    private val rewardRepository: RewardRepository,
    private val statRepository: StatRepository,
    private val dataStoreManager: DataStoreManager
) {
    suspend operator fun invoke(task: Task) {
        val newTask = task.copy(status = !task.status)
        taskRepository.updateTask(task = newTask)

        if (newTask.status)
            dataStoreManager.increaseCoinsBy(coinsAdded = task.coinsReward)
        else
            dataStoreManager.decreaseCoinsBy(coinsRemoved = task.coinsReward)

        rewardRepository.getRewardsForTask(task).collect { rewards ->
            rewards.forEach { reward ->
                val initialStat = statRepository.getStatById(reward.statId)!!
                val finalStat =
                    if (newTask.status) {
                        initialStat.copy(value = initialStat.value + reward.points)
                    } else {
                        initialStat.copy(value = initialStat.value - reward.points)
                    }
                statRepository.updateStat(finalStat)
            }
        }
    }
}
