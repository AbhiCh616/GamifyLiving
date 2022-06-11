package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.TaskRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class DeleteTask @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = runSuspendCatching {
        repository.deleteTask(task)
    }
}
