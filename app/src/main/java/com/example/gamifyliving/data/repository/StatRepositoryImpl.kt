package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.StatDao
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import kotlinx.coroutines.flow.Flow

class StatRepositoryImpl(
    private val statDao: StatDao
) : StatRepository {

    override suspend fun addStat(stat: Stat) {
        statDao.insert(stat)
    }

    override suspend fun updateStat(stat: Stat) {
        statDao.update(stat)
    }

    override suspend fun deleteStat(stat: Stat) {
        statDao.delete(stat)
    }

    override fun observeStats(): Flow<List<Stat>> = statDao.getAll()

}
