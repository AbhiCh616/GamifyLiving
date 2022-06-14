package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.RewardEntity
import com.example.gamifyliving.domain.entity.Reward

fun RewardEntity.toDomainModel() =
    Reward(
        taskId = this.taskId,
        statId = this.statId,
        points = this.points,
        uid = this.id
    )

fun Reward.toDataModel() =
    RewardEntity(
        taskId = this.taskId,
        statId = this.statId,
        points = this.points,
        id = 0
    )

fun List<RewardEntity>.toDomainModel() =
    this.map { rewardEntity -> rewardEntity.toDomainModel() }

fun List<Reward>.toDataModel() =
    this.map { reward -> reward.toDataModel() }
