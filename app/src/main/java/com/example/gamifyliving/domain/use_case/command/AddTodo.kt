package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.repository.TodoRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class AddTodo @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) = runSuspendCatching {

        todoRepository.add(todo = todo)

    }
}
