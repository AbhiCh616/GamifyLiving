package com.example.gamifyliving.application.repository

import com.example.gamifyliving.domain.model.Stat
import kotlinx.coroutines.flow.Flow

interface StatRepository {

    suspend fun addStat(stat: Stat)

    suspend fun updateStat(stat: Stat)

    suspend fun deleteStat(stat: Stat)

    suspend fun getStatById(id: Int): Stat?

    fun observeStats(): Flow<List<Stat>>

}
