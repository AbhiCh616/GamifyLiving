package com.example.gamifyliving.application.repository

import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun increaseCoinsBy(coinsAdded: Int)

    suspend fun decreaseCoinsBy(coinsRemoved: Int)

    fun getCoins(): Flow<Int>

}
