package com.example.gamifyliving.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "StoreItem")

data class StoreItem(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "costCoins")
    val costCoins: Int,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
