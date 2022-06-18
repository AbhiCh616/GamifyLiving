package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.RewardEntity
import com.example.gamifyliving.domain.model.entity.Reward

fun RewardEntity.toReward() =
    Reward(
        statId = this.statId,
        points = this.points
    )

fun List<RewardEntity>.toRewardList() =
    this.map { rewardEntity -> rewardEntity.toReward() }

fun Reward.toRewardEntity(taskId: Int) =
    RewardEntity(
        taskId = taskId,
        statId = statId,
        points = points
    )

fun List<Reward>.toRewardEntityList(taskId: Int) =
    this.map { reward -> reward.toRewardEntity(taskId) }
