package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.entity.Stat
import kotlinx.coroutines.flow.Flow

interface StatRepository {

    fun observe(): Flow<List<Stat>>
    suspend fun getById(id: Int): Stat?

    suspend fun add(stat: Stat)
    suspend fun update(stat: Stat)
    suspend fun delete(stat: Stat)

}
