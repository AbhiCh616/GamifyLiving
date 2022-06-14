package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.RewardEntity
import com.example.gamifyliving.domain.entity.Reward
import com.example.gamifyliving.domain.entity.Task

fun List<RewardEntity>.toDomainModel() =
    this.map { rewardEntity -> rewardEntity.toDomainModel() }