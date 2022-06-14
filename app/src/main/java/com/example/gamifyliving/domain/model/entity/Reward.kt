package com.example.gamifyliving.domain.model.entity

import com.example.gamifyliving.domain.exception.RewardPointsZeroOrNegativeException

data class Reward(
    val statId: Int,
    val points: Int
) {
    init {
        if (points <= 0) {
            throw RewardPointsZeroOrNegativeException(points)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is Reward) {
            return this.statId == other.statId
        }
        return false
    }

    override fun hashCode(): Int {
        return statId
    }
}
