package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.repository.CoinRepository
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.repository.TaskRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import javax.inject.Inject

class ChangeTaskStatus @Inject constructor(
    private val taskRepository: TaskRepository,
    private val statRepository: StatRepository,
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(task: Task) = runSuspendCatching {
        if (task is Todo) {
            // Change (completion) status of task
            val newTask = task.copy(status = !task.status)
            taskRepository.updateTask(task = newTask)

            // Increase or decrease the coins, that user has, based on (un)check status of task
            if (newTask.status)
                coinRepository.increaseCoinsBy(coinsAdded = task.coinsReward)
            else
                coinRepository.decreaseCoinsBy(coinsRemoved = task.coinsReward)

            // Increase or decrease the stats based on (un)check status

            task.rewards?.forEach { reward ->
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
        if (task is Habit) {
            // Change (completion) status of task
            val newTask = task.copy(status = !task.status)
            taskRepository.updateTask(task = newTask)

            // Increase or decrease the stats based on (un)check status
            task.rewards?.forEach { reward ->
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
