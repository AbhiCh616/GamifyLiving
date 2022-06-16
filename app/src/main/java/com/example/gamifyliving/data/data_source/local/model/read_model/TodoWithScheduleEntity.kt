package com.example.gamifyliving.data.data_source.local.model.read_model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.gamifyliving.data.data_source.local.model.table.TodoEntity
import com.example.gamifyliving.data.data_source.local.model.table.DateScheduleEntity

data class TodoWithScheduleEntity(

    @Embedded val todo: TodoEntity,

    @Relation(
        parentColumn = "task_id",
        entityColumn = "todo_id"
    )
    val todoSchedule: DateScheduleEntity?

)
