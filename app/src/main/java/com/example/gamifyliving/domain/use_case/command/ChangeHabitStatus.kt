package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class ChangeHabitStatus @Inject constructor(
    private val statRepository: StatRepository,
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) = runSuspendCatching {

        // Change (completion) status of habit
        val updatedHabit = habit.copy(status = !habit.status)
        habitRepository.update(habit = updatedHabit)

        // Increase or decrease the stats based on (un)check status
        habit.rewards?.forEach { reward ->
            val initialStat = statRepository.getById(reward.statId)!!
            val finalStat =
                if (updatedHabit.status) {
                    initialStat.copy(value = initialStat.value + reward.points)
                } else {
                    initialStat.copy(value = initialStat.value - reward.points)
                }
            statRepository.update(stat = finalStat)
        }

    }
}
