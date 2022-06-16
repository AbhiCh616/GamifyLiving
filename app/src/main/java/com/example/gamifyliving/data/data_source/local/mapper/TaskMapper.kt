package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.TaskEntity
import com.example.gamifyliving.domain.model.entity.Task

fun Task.toTaskEntity() =
    TaskEntity(
        name = name,
        status = status,
        id = id
    )
