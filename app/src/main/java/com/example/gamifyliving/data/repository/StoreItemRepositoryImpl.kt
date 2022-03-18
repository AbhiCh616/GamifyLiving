package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.StoreItemDao
import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreItemRepositoryImpl @Inject constructor(
    private val storeItemDao: StoreItemDao
) : StoreItemRepository {

    override suspend fun addItem(item: StoreItem) {
        storeItemDao.insert(item)
    }

    override suspend fun updateItem(item: StoreItem) {
        storeItemDao.update(item)
    }

    override suspend fun deleteItem(item: StoreItem) {
        storeItemDao.delete(item)
    }

    override suspend fun getStoreItemById(id: Int): StoreItem? =
        storeItemDao.getStoreItemById(id)

    override fun observeItems(): Flow<List<StoreItem>> = storeItemDao.getAll()

}
