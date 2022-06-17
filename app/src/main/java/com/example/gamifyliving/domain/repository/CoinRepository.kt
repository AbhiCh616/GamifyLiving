package com.example.gamifyliving.domain.repository

import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun update(coins: Int)

    fun get(): Flow<Int>

}
