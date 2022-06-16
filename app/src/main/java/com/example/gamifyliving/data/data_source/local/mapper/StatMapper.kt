package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.StatEntity
import com.example.gamifyliving.domain.model.entity.Stat

fun StatEntity.toStat() =
    Stat(
        name = this.name,
        value = this.value,
        id = this.id
    )

fun Stat.toStatEntity() =
    StatEntity(
        name = this.name,
        value = this.value,
        id = this.id
    )

fun List<StatEntity>.toStatList() =
    this.map { statEntity -> statEntity.toStat() }
