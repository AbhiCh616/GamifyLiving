package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import javax.inject.Inject

class DeleteStat @Inject constructor(
    private val repository: StatRepository
) {
    suspend operator fun invoke(stat: Stat) {
        repository.deleteStat(stat)
    }
}
