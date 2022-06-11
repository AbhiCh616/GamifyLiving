package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class UpdateStat @Inject constructor(
    private val repository: StatRepository
) {
    suspend operator fun invoke(stat: Stat) = runSuspendCatching {
        repository.updateStat(stat)
    }
}
