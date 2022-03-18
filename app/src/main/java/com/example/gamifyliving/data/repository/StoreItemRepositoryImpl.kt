package com.example.gamifyliving.data.repository

import com.example.gamifyliving.data.data_source.local.StoreItemDAO
import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreItemRepositoryImpl @Inject constructor(
    private val storeItemDAO: StoreItemDAO
) : StoreItemRepository {

    override suspend fun addItem(item: StoreItem) {
        storeItemDAO.insert(item)
    }

    override suspend fun updateItem(item: StoreItem) {
        storeItemDAO.update(item)
    }

    override suspend fun deleteItem(item: StoreItem) {
        storeItemDAO.delete(item)
    }

    override suspend fun getStoreItemById(id: Int): StoreItem? =
        storeItemDAO.getStoreItemById(id)

    override fun observeItems(): Flow<List<StoreItem>> = storeItemDAO.getAll()

}
