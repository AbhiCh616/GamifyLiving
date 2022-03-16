package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreItems @Inject constructor(
    private val repository: StoreItemRepository
) {
    operator fun invoke(): Flow<List<StoreItem>> = repository.observeItems()
}
