package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.RewardEntity

fun List<RewardEntity>.toDomainModel() =
    this.map { rewardEntity -> rewardEntity.toDomainModel() }