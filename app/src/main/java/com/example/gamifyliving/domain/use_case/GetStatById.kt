package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class GetStatById @Inject constructor(
    private val repository: StatRepository
) {
    suspend operator fun invoke(id: Int) = runSuspendCatching { repository.getStatById(id = id) }
}
