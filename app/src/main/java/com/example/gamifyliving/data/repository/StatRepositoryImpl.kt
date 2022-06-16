package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.StatDao
import com.example.gamifyliving.data.data_source.local.mapper.toStatEntity
import com.example.gamifyliving.data.data_source.local.mapper.toStatList
import com.example.gamifyliving.data.data_source.local.mapper.toStat
import com.example.gamifyliving.domain.model.entity.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StatRepositoryImpl @Inject constructor(
    private val statDao: StatDao
) : StatRepository {

    override suspend fun addStat(stat: Stat) {
        statDao.insert(stat.toStatEntity())
    }

    override suspend fun updateStat(stat: Stat) {
        statDao.update(stat.toStatEntity())
    }

    override suspend fun deleteStat(stat: Stat) {
        statDao.delete(stat.toStatEntity())
    }

    override suspend fun getStatById(id: Int): Stat? = statDao.getById(id)?.toStat()

    override fun observeStats(): Flow<List<Stat>> = statDao.getAll().map {
        it.toStatList()
    }

}
