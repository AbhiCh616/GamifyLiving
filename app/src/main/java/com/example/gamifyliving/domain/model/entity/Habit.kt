package com.example.gamifyliving.domain.model.entity

import com.example.gamifyliving.domain.exception.DuplicateRewardsInsideTask
import com.example.gamifyliving.domain.exception.TaskNameEmptyException
import com.example.gamifyliving.domain.model.value_object.Schedule

data class Habit(
    override val name: String,
    override val schedule: Schedule? = null,
    override val status: Boolean = false,
    override val rewards: List<Reward>? = null,
    override val id: Int = 0,
) : Task {
    init {
        if (name.replace(" ", "").isEmpty()) {
            throw TaskNameEmptyException()
        }
        if (rewards?.distinct()?.count() != rewards?.count()) {
            throw DuplicateRewardsInsideTask()
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other is Habit) {
            return this.id == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        return id
    }
}
