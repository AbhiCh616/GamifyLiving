package com.example.gamifyliving.domain.model

data class Reward(
    val taskId: Int,
    val statId: Int,
    val points: Int,
    val uid: Int = 0
)
