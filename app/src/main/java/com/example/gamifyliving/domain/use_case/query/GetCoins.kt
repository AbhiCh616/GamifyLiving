package com.example.gamifyliving.domain.use_case.query

import com.example.gamifyliving.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoins @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Int> = repository.observe()
}
