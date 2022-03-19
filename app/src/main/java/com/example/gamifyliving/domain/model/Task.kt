package com.example.gamifyliving.domain.model

data class Task(
    val name: String,
    val status: Boolean = false,
    val coinsReward: Int = 0,
    val uid: Int = 0
)
