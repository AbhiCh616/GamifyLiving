package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.StoreItemDao
import com.example.gamifyliving.data.data_source.local.mapper.toStoreItem
import com.example.gamifyliving.data.data_source.local.mapper.toStoreItemEntity
import com.example.gamifyliving.data.data_source.local.mapper.toStoreItemList
import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreItemRepositoryImpl @Inject constructor(
    private val storeItemDao: StoreItemDao
) : StoreItemRepository {

    override fun observe() = storeItemDao.getAll().map {
        it.toStoreItemList()
    }

    override suspend fun getById(id: Int) =
        storeItemDao.getById(id)?.toStoreItem()

    override suspend fun add(item: StoreItem) {
        storeItemDao.insert(item.toStoreItemEntity())
    }

    override suspend fun update(item: StoreItem) {
        storeItemDao.update(item.toStoreItemEntity())
    }

    override suspend fun delete(item: StoreItem) {
        storeItemDao.delete(item.toStoreItemEntity())
    }

}
