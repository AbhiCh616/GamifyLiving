package com.example.gamifyliving.data.data_source.local.mapper

import com.example.gamifyliving.data.data_source.local.model.table.StoreItemEntity
import com.example.gamifyliving.domain.model.entity.StoreItem

fun StoreItemEntity.toStoreItem() =
    StoreItem(
        name = this.name,
        costCoins = this.costCoins,
        id = this.id
    )

fun List<StoreItemEntity>.toStoreItemList() =
    this.map { storeItemEntity -> storeItemEntity.toStoreItem() }

fun StoreItem.toStoreItemEntity() =
    StoreItemEntity(
        name = this.name,
        costCoins = this.costCoins,
        id = this.id
    )
