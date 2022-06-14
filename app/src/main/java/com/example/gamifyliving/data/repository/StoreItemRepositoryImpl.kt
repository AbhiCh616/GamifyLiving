package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.StoreItemDao
import com.example.gamifyliving.data.data_source.local.mapper.toDataModel
import com.example.gamifyliving.data.data_source.local.mapper.toDomainModel
import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreItemRepositoryImpl @Inject constructor(
    private val storeItemDao: StoreItemDao
) : StoreItemRepository {

    override suspend fun addItem(item: StoreItem) {
        storeItemDao.insert(item.toDataModel())
    }

    override suspend fun updateItem(item: StoreItem) {
        storeItemDao.update(item.toDataModel())
    }

    override suspend fun deleteItem(item: StoreItem) {
        storeItemDao.delete(item.toDataModel())
    }

    override suspend fun getStoreItemById(id: Int): StoreItem? =
        storeItemDao.getStoreItemById(id)?.toDomainModel()

    override fun observeItems(): Flow<List<StoreItem>> = storeItemDao.getAll().map {
        it.toDomainModel()
    }

}
