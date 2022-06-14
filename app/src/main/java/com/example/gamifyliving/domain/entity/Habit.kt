package com.example.gamifyliving.domain.entity

data class Habit(
    override val name: String,
    override val schedule: Schedule? = null,
    override val status: Boolean = false,
    override val id: Int = 0,
) : Task
