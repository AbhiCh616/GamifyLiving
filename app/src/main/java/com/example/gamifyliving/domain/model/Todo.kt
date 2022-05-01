package com.example.gamifyliving.domain.model

data class Todo(
    override val name: String,
    val coinsReward: Int = 0,
    override val schedule: DateSchedule? = null,
    override val status: Boolean = false,
    override val id: Int = 0
) : Task
