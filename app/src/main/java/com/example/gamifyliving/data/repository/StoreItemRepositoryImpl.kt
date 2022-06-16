package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.dao.StoreItemDao
import com.example.gamifyliving.data.data_source.local.mapper.toStoreItemEntity
import com.example.gamifyliving.data.data_source.local.mapper.toStoreItemList
import com.example.gamifyliving.data.data_source.local.mapper.toStoreItem
import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreItemRepositoryImpl @Inject constructor(
    private val storeItemDao: StoreItemDao
) : StoreItemRepository {

    override suspend fun addItem(item: StoreItem) {
        storeItemDao.insert(item.toStoreItemEntity())
    }

    override suspend fun updateItem(item: StoreItem) {
        storeItemDao.update(item.toStoreItemEntity())
    }

    override suspend fun deleteItem(item: StoreItem) {
        storeItemDao.delete(item.toStoreItemEntity())
    }

    override suspend fun getStoreItemById(id: Int): StoreItem? =
        storeItemDao.getStoreItemById(id)?.toStoreItem()

    override fun observeItems(): Flow<List<StoreItem>> = storeItemDao.getAll().map {
        it.toStoreItemList()
    }

}
