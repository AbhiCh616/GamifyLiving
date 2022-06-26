package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.di.ApplicationScope
import com.example.gamifyliving.di.DefaultDispatcher
import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.repository.TodoRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTask @Inject constructor(
    private val todoRepository: TodoRepository,
    private val habitRepository: HabitRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    @ApplicationScope private val scope: CoroutineScope,
) {
    suspend operator fun invoke(task: Task) = runSuspendCatching {
        scope.launch(dispatcher) {
            if (task is Todo) {
                todoRepository.add(task)
            }
            if (task is Habit) {
                habitRepository.add(task)
            }
        }
    }
}
