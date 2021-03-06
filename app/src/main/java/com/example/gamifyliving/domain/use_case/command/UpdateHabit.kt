package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class UpdateHabit @Inject constructor(
    private val repository: HabitRepository
) {
    suspend operator fun invoke(habit: Habit) = runSuspendCatching {

        repository.update(habit = habit)

    }
}
