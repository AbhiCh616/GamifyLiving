package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.repository.TodoRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class DeleteTodo @Inject constructor(
    private val repository: TodoRepository
) {
    suspend operator fun invoke(todo: Todo) = runSuspendCatching {

        repository.delete(todo = todo)

    }
}
