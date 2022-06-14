package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.StatEntity
import com.example.gamifyliving.domain.model.entity.Stat

fun StatEntity.toDomainModel() =
    Stat(
        name = this.name,
        value = this.value,
        uid = this.id
    )

fun Stat.toDataModel() =
    StatEntity(
        name = this.name,
        value = this.value,
        id = this.uid
    )

fun List<StatEntity>.toDomainModel() =
    this.map { statEntity -> statEntity.toDomainModel() }

fun List<Stat>.toDataModel() =
    this.map { stat -> stat.toDataModel() }
