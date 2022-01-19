package com.example.gamifyliving.repository

import com.example.gamifyliving.data.database.StatDao
import com.example.gamifyliving.data.model.Stat
import kotlinx.coroutines.flow.Flow

class StatRepository(
    private val statDao: StatDao
) {

    suspend fun addStat(stat: Stat) {
        statDao.insert(stat)
    }

    suspend fun updateStat(stat: Stat) {
        statDao.update(stat)
    }

    suspend fun deleteStat(stat: Stat) {
        statDao.delete(stat)
    }

    fun observeStats(): Flow<List<Stat>> = statDao.getAll()

}
