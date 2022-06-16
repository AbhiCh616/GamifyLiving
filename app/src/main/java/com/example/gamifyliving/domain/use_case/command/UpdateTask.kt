package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.util.runSuspendCatching
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.repository.TodoRepository
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateTask @Inject constructor(
    private val todoRepository: TodoRepository,
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(task: Task) = runSuspendCatching {
        withContext(NonCancellable) {
            if(task is Todo)
            {
                todoRepository.add(task)
            }
            if(task is Habit)
            {
                habitRepository.add(task)
            }
        }
    }
}
