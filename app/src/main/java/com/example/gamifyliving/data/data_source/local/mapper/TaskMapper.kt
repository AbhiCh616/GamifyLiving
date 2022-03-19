package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.TaskEntity
import com.example.gamifyliving.domain.model.Task

fun TaskEntity.toDomainModel() =
    Task(
        name = this.name,
        status = this.status,
        coinsReward = this.coinsReward,
        uid = this.uid
    )

fun Task.toDataModel() =
    TaskEntity(
        name = this.name,
        status = this.status,
        coinsReward = this.coinsReward,
        uid = this.uid
    )
