package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.RewardEntity
import com.example.gamifyliving.domain.model.Reward

fun RewardEntity.toDomainModel() =
    Reward(
        taskId = this.taskId,
        statId = this.statId,
        points = this.points,
        uid = this.uid
    )

fun Reward.toDataModel() =
    RewardEntity(
        taskId = this.taskId,
        statId = this.statId,
        points = this.points,
        uid = this.uid
    )
