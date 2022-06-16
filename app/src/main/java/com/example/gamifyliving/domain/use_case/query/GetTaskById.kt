package com.example.gamifyliving.domain.use_case.query

import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.repository.TodoRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class GetTaskById @Inject constructor(
    private val todoRepository: TodoRepository,
    private val habitRepository: HabitRepository
) {
    suspend operator fun invoke(id: Int) = runSuspendCatching {
        todoRepository.getById(id = id) ?: habitRepository.getById(id = id)
    }
}
