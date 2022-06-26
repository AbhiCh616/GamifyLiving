package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class AddHabit @Inject constructor(
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) = runSuspendCatching {

        habitRepository.add(habit = habit)

    }
}
