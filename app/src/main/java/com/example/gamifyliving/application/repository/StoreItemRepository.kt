package com.example.gamifyliving.application.repository

import com.example.gamifyliving.domain.model.StoreItem
import kotlinx.coroutines.flow.Flow

interface StoreItemRepository {

    suspend fun addItem(item: StoreItem)

    suspend fun updateItem(item: StoreItem)

    suspend fun deleteItem(item: StoreItem)

    suspend fun getStoreItemById(id: Int): StoreItem?

    fun observeItems(): Flow<List<StoreItem>>

}
