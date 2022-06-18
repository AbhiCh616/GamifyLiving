package com.example.gamifyliving.domain.use_case.query

import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStoreItems @Inject constructor(
    private val repository: StoreItemRepository
) {
    operator fun invoke(): Flow<List<StoreItem>> = repository.observe()
}
