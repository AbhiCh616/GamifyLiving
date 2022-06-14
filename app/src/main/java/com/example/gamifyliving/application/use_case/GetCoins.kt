package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.application.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoins @Inject constructor(
    private val coinsRepository: CoinRepository
) {
    operator fun invoke(): Flow<Int> = coinsRepository.getCoins()
}
