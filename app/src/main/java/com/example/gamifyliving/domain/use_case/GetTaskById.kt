package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.repository.TaskRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class GetTaskById @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int) = runSuspendCatching { repository.getTaskById(id = id) }
}
