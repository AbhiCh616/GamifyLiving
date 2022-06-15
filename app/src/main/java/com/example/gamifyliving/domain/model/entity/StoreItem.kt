package com.example.gamifyliving.domain.model.entity

import com.example.gamifyliving.domain.exception.StoreItemCostZeroOrNegativeException
import com.example.gamifyliving.domain.exception.StoreItemNameEmptyException

data class StoreItem(
    val name: String,
    val costCoins: Int,
    val id: Int = 0
) {
    init {
        if (name.replace(" ", "").isEmpty()) {
            throw StoreItemNameEmptyException()
        }
        if (costCoins <= 0) {
            throw StoreItemCostZeroOrNegativeException(costCoins)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is StoreItem) {
            return this.id == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}
