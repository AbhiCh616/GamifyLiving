package com.example.gamifyliving.presentation.util

import com.example.gamifyliving.domain.model.entity.Reward

data class RewardUIModel(
    val taskId: Int,
    val statId: Int,
    val points: Int,
    val uid: Int
)

fun RewardUIModel.toDomainModel() =
    Reward(
        statId = statId,
        points = points
    )

fun Reward.toUIModel(taskId: Int, index: Int) =
    RewardUIModel(
        statId = this.statId,
        points = this.points,
        taskId = taskId,
        uid = index
    )

fun List<RewardUIModel>.toDomainModel() =
    this.map { reward -> reward.toDomainModel() }