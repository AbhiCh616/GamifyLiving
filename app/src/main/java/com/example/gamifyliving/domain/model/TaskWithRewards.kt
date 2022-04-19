package com.example.gamifyliving.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class TaskWithRewards(
    val uid: Int = 0,
    val name: String,
    val status: Boolean = false,
    val coinsReward: Int = 0,
    val scheduledDate: LocalDate? = null,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val rewards: List<Reward> = emptyList()
) {
    constructor(task: Task, rewards: List<Reward>) : this(
        uid = task.uid,
        name = task.name,
        status = task.status,
        coinsReward = task.coinsReward,
        scheduledDate = task.scheduledDate,
        startTime = task.startTime,
        endTime = task.endTime,
        rewards = rewards
    )
}

