package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.DataStoreManager
import com.example.gamifyliving.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : CoinRepository {

    override suspend fun update(coins: Int) {
        dataStoreManager.update(coins)
    }

    override fun get(): Flow<Int> = dataStoreManager.coins

}
