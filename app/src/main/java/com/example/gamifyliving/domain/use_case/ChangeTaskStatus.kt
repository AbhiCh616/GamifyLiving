package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.TaskRepository
import javax.inject.Inject

class ChangeTaskStatus @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task) {
        val newTask = task.copy(status = !task.status)
        repository.updateTask(task = newTask)
    }
}
