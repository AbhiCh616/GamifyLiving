package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.domain.repository.CoinRepository
import javax.inject.Inject

class BuyStoreItem @Inject constructor(
    private val coinRepository: CoinRepository
) {
    suspend operator fun invoke(storeItem: StoreItem) {
        coinRepository.decreaseCoinsBy(storeItem.costCoins)
    }
}
