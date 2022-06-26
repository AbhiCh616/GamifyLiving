package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class AddStat @Inject constructor(
    private val repository: StatRepository
) {
    suspend operator fun invoke(stat: Stat) = runSuspendCatching {

        repository.add(stat = stat)

    }
}
