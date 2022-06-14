package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.application.repository.StoreItemRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import javax.inject.Inject

class GetStoreItemById @Inject constructor(
    private val repository: StoreItemRepository
) {
    suspend operator fun invoke(id: Int) = runSuspendCatching { repository.getStoreItemById(id) }
}