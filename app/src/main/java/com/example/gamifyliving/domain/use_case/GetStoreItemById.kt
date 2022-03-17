package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import javax.inject.Inject

class GetStoreItemById @Inject constructor(
    private val repository: StoreItemRepository
) {
    suspend operator fun invoke(id: Int): StoreItem? = repository.getStoreItemById(id)
}