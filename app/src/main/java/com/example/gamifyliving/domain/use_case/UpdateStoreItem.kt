package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.repository.StoreItemRepository
import javax.inject.Inject

class UpdateStoreItem @Inject constructor(
    private val repository: StoreItemRepository
) {
    suspend operator fun invoke(item: StoreItem) {
        repository.updateItem(item)
    }
}
