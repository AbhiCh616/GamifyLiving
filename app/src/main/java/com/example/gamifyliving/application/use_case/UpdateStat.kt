package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.application.repository.StatRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import javax.inject.Inject

class UpdateStat @Inject constructor(
    private val repository: StatRepository
) {
    suspend operator fun invoke(stat: Stat) = runSuspendCatching {
        repository.updateStat(stat)
    }
}
