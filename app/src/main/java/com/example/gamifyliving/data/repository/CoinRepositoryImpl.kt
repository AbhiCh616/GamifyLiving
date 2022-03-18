package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.DataStoreManager
import com.example.gamifyliving.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : CoinRepository {

    override suspend fun increaseCoinsBy(coinsAdded: Int) {
        dataStoreManager.increaseCoinsBy(coinsAdded)
    }

    override suspend fun decreaseCoinsBy(coinsRemoved: Int) {
        dataStoreManager.decreaseCoinsBy(coinsRemoved)
    }

    override fun getCoins(): Flow<Int> = dataStoreManager.coins

}
