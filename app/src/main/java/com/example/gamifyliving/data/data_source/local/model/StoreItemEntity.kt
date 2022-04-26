package com.example.gamifyliving.data.data_source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "store_item")

data class StoreItemEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "cost_coins")
    val costCoins: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)
