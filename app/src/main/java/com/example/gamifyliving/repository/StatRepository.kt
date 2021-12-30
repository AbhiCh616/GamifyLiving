package com.example.gamifyliving.repository

import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.data.database.StatDao
import kotlinx.coroutines.flow.Flow

class StatRepository(
    private val statDao: StatDao
) {
    val allStats: Flow<List<Stat>> = statDao.getAll()

    suspend fun getStatByName(statName: String) {
        statDao.get(statName)
    }

    suspend fun addStat(stat: Stat) {
        statDao.insert(stat)
    }

    suspend fun updateStat(stat: Stat) {
        statDao.update(stat)
    }

    suspend fun deleteStat(stat: Stat) {
        statDao.delete(stat)
    }
}