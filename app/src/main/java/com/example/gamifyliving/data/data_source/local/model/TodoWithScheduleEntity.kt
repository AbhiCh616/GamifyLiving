package com.example.gamifyliving.data.data_source.local.model

import androidx.room.Embedded
import androidx.room.Relation

data class TodoWithScheduleEntity(
    @Embedded val todo: TodoEntity,

    @Relation(
        parentColumn = "id",
        entityColumn = "todo_id"
    )
    val todoSchedule: TodoScheduleEntity?
)