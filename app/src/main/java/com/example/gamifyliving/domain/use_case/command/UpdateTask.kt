package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.repository.TaskRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import com.example.gamifyliving.domain.model.entity.Task
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateTask @Inject constructor(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(task: Task) = runSuspendCatching {
        withContext(NonCancellable) {
            // Update task
            taskRepository.updateTask(task)
        }
    }
}
