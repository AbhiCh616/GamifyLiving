package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.application.repository.CoinRepository
import com.example.gamifyliving.application.util.runSuspendCatching
import javax.inject.Inject

class BuyStoreItem @Inject constructor(
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(storeItem: StoreItem) = runSuspendCatching {
        coinRepository.decreaseCoinsBy(storeItem.costCoins)
    }
}
