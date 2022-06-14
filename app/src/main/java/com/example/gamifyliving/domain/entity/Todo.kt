package com.example.gamifyliving.domain.entity

data class Todo(
    override val name: String,
    val coinsReward: Int = 0,
    override val schedule: DateSchedule? = null,
    override val status: Boolean = false,
    override val rewards: List<Reward>? = null,
    override val id: Int = 0
) : Task
