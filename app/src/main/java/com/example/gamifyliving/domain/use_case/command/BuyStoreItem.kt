package com.example.gamifyliving.domain.use_case.command

import com.example.gamifyliving.domain.model.entity.StoreItem
import com.example.gamifyliving.domain.repository.CoinRepository
import com.example.gamifyliving.domain.util.runSuspendCatching
import javax.inject.Inject

class BuyStoreItem @Inject constructor(
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(storeItem: StoreItem) = runSuspendCatching {
        coinRepository.observe().collect { coins ->
            coinRepository.update(coins - storeItem.costCoins)
        }
    }
}
