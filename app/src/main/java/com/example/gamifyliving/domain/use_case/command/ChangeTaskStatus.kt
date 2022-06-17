package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.repository.CoinRepository
import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.repository.TodoRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class ChangeTaskStatus @Inject constructor(
    private val statRepository: StatRepository,
    private val coinRepository: CoinRepository,
    private val todoRepository: TodoRepository,
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(task: Task) = runSuspendCatching {
        if (task is Todo) {
            // Change (completion) status of task
            val newTask = task.copy(status = !task.status)
            todoRepository.update(todo = newTask)

            // Increase or decrease the coins, that user has, based on (un)check status of task
            coinRepository.get().collect { coins ->
                var updatedCoins = coins
                if (newTask.status) {
                    updatedCoins += task.coinsReward
                } else {
                    updatedCoins -= task.coinsReward
                }
                coinRepository.update(updatedCoins)
            }

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
            habitRepository.update(habit = newTask)

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
