package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.util.DataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCoins @Inject constructor(
    private val dataStoreManager: DataStoreManager
) {
    operator fun invoke(): Flow<Int> = dataStoreManager.coins
}
