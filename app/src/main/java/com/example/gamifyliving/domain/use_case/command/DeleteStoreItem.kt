package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class DeleteStoreItem @Inject constructor(
    private val repository: StoreItemRepository
) {
    suspend operator fun invoke(item: StoreItem) = runSuspendCatching {
        repository.deleteItem(item)
    }
}
