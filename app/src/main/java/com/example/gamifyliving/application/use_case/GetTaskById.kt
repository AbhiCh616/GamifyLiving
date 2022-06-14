package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.application.repository.TaskRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import javax.inject.Inject

class GetTaskById @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int) = runSuspendCatching { repository.getTaskById(id = id) }
}
