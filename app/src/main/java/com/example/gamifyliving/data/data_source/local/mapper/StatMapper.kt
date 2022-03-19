package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.StatEntity
import com.example.gamifyliving.domain.model.Stat

fun StatEntity.toDomainModel() =
    Stat(
        name = this.name,
        value = this.value,
        uid = this.uid
    )

fun Stat.toDataModel() =
    StatEntity(
        name = this.name,
        value = this.value,
        uid = this.uid
    )
