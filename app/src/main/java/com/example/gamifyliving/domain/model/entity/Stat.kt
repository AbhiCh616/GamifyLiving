package com.example.gamifyliving.domain.model.entity

import com.example.gamifyliving.domain.exception.StatNameEmptyException
import com.example.gamifyliving.domain.exception.StatValueOutOfRangeException

data class Stat(
    val name: String,
    val value: Int
) {
    init {
        if (name.replace(" ", "").isEmpty()) {
            throw StatNameEmptyException()
        }
        if (value < 0 || value > 500) {
            throw StatValueOutOfRangeException(value)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is Stat) {
            return this.name == other.name
        }
        return false
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
