package com.example.gamifyliving.domain.entity

data class Reward(
    val taskId: Int,
    val statId: Int,
    val points: Int,
    val uid: Int = 0
)
