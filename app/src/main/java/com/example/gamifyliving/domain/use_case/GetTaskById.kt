package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.repository.TaskRepository
import javax.inject.Inject

class GetTaskById @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int): Task? = repository.getTaskById(id = id)
}
