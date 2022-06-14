package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.StatDao
import com.example.gamifyliving.data.data_source.local.mapper.toDataModel
import com.example.gamifyliving.data.data_source.local.mapper.toDomainModel
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.application.repository.StatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StatRepositoryImpl @Inject constructor(
    private val statDao: StatDao
) : StatRepository {

    override suspend fun addStat(stat: Stat) {
        statDao.insert(stat.toDataModel())
    }

    override suspend fun updateStat(stat: Stat) {
        statDao.update(stat.toDataModel())
    }

    override suspend fun deleteStat(stat: Stat) {
        statDao.delete(stat.toDataModel())
    }

    override suspend fun getStatById(id: Int): Stat? = statDao.getStatById(id)?.toDomainModel()

    override fun observeStats(): Flow<List<Stat>> = statDao.getAll().map {
        it.toDomainModel()
    }

}
