package com.example.gamifyliving.domain.model.entity

data class Habit(
    override val name: String,
    override val schedule: Schedule? = null,
    override val status: Boolean = false,
    override val rewards: List<Reward>? = null,
    override val id: Int = 0,
) : Task
