package com.example.gamifyliving.domain.repository

import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    fun observe(): Flow<Int>

    suspend fun update(coins: Int)

}
