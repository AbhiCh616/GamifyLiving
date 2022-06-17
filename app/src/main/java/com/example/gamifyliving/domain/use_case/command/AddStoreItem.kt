package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class AddStoreItem @Inject constructor(
    private val repository: StoreItemRepository
) {
    suspend operator fun invoke(item: StoreItem) = runSuspendCatching {
        repository.add(item)
    }
}
