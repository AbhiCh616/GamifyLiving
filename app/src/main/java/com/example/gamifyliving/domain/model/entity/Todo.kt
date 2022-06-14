package com.example.gamifyliving.domain.model.entity

import com.example.gamifyliving.domain.exception.DuplicateRewardsInsideTask
import com.example.gamifyliving.domain.exception.TaskNameEmptyException
import com.example.gamifyliving.domain.exception.TaskRewardNegativeException
import com.example.gamifyliving.domain.model.value_object.DateSchedule

data class Todo(
    override val name: String,
    val coinsReward: Int = 0,
    override val schedule: DateSchedule? = null,
    override val status: Boolean = false,
    override val rewards: List<Reward>? = null,
    override val id: Int = 0
) : Task {
    init {
        if (name.replace(" ", "").isEmpty()) {
            throw TaskNameEmptyException()
        }
        if (coinsReward < 0) {
            throw TaskRewardNegativeException(coinsReward)
        }
        if (rewards?.distinct()?.count() != rewards?.count()) {
            throw DuplicateRewardsInsideTask()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is Todo) {
            return this.id == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}
