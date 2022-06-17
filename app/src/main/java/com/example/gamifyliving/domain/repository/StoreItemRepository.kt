package com.example.gamifyliving.domain.repository

import com.example.gamifyliving.domain.model.entity.StoreItem
import kotlinx.coroutines.flow.Flow

interface StoreItemRepository {

    fun observe(): Flow<List<StoreItem>>
    suspend fun getById(id: Int): StoreItem?

    suspend fun add(item: StoreItem)
    suspend fun update(item: StoreItem)
    suspend fun delete(item: StoreItem)

}
