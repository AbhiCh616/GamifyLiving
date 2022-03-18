package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.StatDao
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StatRepositoryImpl @Inject constructor(
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

    override suspend fun getStatById(id: Int): Stat? = statDao.getStatById(id)

    override fun observeStats(): Flow<List<Stat>> = statDao.getAll()

}
