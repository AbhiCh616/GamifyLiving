package com.example.gamifyliving.data.data_source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StoreItem")

data class StoreItemEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "costCoins")
    val costCoins: Int,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
