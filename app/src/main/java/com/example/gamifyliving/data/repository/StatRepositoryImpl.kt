package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.StatDao
import com.example.gamifyliving.data.data_source.local.mapper.toStat
import com.example.gamifyliving.data.data_source.local.mapper.toStatEntity
import com.example.gamifyliving.data.data_source.local.mapper.toStatList
import com.example.gamifyliving.domain.model.entity.Stat
import com.example.gamifyliving.domain.repository.StatRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StatRepositoryImpl @Inject constructor(
    private val statDao: StatDao
) : StatRepository {

    override fun observe() = statDao.getAll().map {
        it.toStatList()
    }

    override suspend fun getById(id: Int) = statDao.getById(id)?.toStat()

    override suspend fun add(stat: Stat) {
        statDao.insert(stat.toStatEntity())
    }

    override suspend fun update(stat: Stat) {
        statDao.update(stat.toStatEntity())
    }

    override suspend fun delete(stat: Stat) {
        statDao.delete(stat.toStatEntity())
    }

}
