package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.application.repository.TaskRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import com.example.gamifyliving.domain.entity.Task
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddTask @Inject constructor(
    private val taskRepository: TaskRepository
) {
    suspend operator fun invoke(task: Task) = runSuspendCatching {
        withContext(NonCancellable) {
            val taskId = taskRepository.addTask(task)
        }
    }
}
