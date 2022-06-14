package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.domain.entity.StoreItem
import com.example.gamifyliving.application.repository.StoreItemRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import javax.inject.Inject

class DeleteStoreItem @Inject constructor(
    private val repository: StoreItemRepository
) {
    suspend operator fun invoke(item: StoreItem) = runSuspendCatching {
        repository.deleteItem(item)
    }
}
