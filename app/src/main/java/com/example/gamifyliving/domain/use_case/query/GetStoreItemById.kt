package com.example.gamifyliving.domain.use_case.query

import com.example.gamifyliving.domain.repository.StoreItemRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class GetStoreItemById @Inject constructor(
    private val repository: StoreItemRepository
) {
    suspend operator fun invoke(id: Int) = runSuspendCatching { repository.getStoreItemById(id) }
}