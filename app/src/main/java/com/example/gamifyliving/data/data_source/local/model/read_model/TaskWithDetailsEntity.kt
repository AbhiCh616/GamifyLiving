package com.example.gamifyliving.data.data_source.local.model.read_model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gamifyliving.data.data_source.local.model.table.TodoEntity
import com.example.gamifyliving.data.data_source.local.model.table.HabitEntity
import com.example.gamifyliving.data.data_source.local.model.table.RewardEntity
import com.example.gamifyliving.data.data_source.local.model.table.TaskEntity

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
