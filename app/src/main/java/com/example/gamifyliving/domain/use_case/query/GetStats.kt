package com.example.gamifyliving.domain.use_case.query

import com.example.gamifyliving.domain.model.entity.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStats @Inject constructor(
    private val repository: StatRepository
) {
    operator fun invoke(): Flow<List<Stat>> = repository.observe()
}
