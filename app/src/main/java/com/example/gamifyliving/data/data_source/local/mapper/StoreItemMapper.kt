package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.StoreItemEntity
import com.example.gamifyliving.domain.model.StoreItem

fun StoreItemEntity.toDomainModel() =
    StoreItem(
        name = this.name,
        costCoins = this.costCoins,
        uid = this.id
    )

fun StoreItem.toDataModel() =
    StoreItemEntity(
        name = this.name,
        costCoins = this.costCoins,
        id = this.uid
    )

fun List<StoreItemEntity>.toDomainModel() =
    this.map { storeItemEntity -> storeItemEntity.toDomainModel() }

fun List<StoreItem>.toDataModel() =
    this.map { storeItem -> storeItem.toDataModel() }
