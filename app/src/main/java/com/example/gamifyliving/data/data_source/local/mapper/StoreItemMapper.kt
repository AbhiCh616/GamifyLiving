package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.StoreItemEntity
import com.example.gamifyliving.domain.model.StoreItem

fun StoreItemEntity.toDomainModel() =
    StoreItem(
        name = this.name,
        costCoins = this.costCoins,
        uid = this.uid
    )

fun StoreItem.toDataModel() =
    StoreItemEntity(
        name = this.name,
        costCoins = this.costCoins,
        uid = this.uid
    )
