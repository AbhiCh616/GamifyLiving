package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.domain.entity.StoreItem
import com.example.gamifyliving.application.repository.StoreItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreItems @Inject constructor(
    private val repository: StoreItemRepository
) {
    operator fun invoke(): Flow<List<StoreItem>> = repository.observeItems()
}
