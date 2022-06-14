package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.entity.StoreItem
import kotlinx.coroutines.flow.Flow

interface StoreItemRepository {

    suspend fun addItem(item: StoreItem)

    suspend fun updateItem(item: StoreItem)

    suspend fun deleteItem(item: StoreItem)

    suspend fun getStoreItemById(id: Int): StoreItem?

    fun observeItems(): Flow<List<StoreItem>>

}
