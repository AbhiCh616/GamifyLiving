package com.example.gamifyliving.data.data_source.local.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gamifyliving.domain.entity.Reward

data class TaskWithDetailsEntity(
    @Embedded val task: TaskEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        entity = TodoEntity::class
    )
    val todoWithSchedule: TodoWithScheduleEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        entity = HabitEntity::class
    )
    val habitWithSchedule: HabitWithScheduleEntity?,

    @Relation(
        parentColumn = "id",
        entityColumn = "task_id",
        entity = RewardEntity::class
    )
    val rewards: List<RewardEntity>?
)
