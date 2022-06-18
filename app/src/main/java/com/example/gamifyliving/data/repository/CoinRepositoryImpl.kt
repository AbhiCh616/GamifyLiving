package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.DataStoreManager
import com.example.gamifyliving.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : CoinRepository {

    override fun observe() = dataStoreManager.coins

    override suspend fun update(coins: Int) {
        dataStoreManager.update(coins)
    }

}
