package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.application.repository.StatRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import javax.inject.Inject

class GetStatById @Inject constructor(
    private val repository: StatRepository
) {
    suspend operator fun invoke(id: Int) = runSuspendCatching { repository.getStatById(id = id) }
}
