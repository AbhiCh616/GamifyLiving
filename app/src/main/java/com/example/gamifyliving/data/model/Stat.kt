package com.example.gamifyliving.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity (indices = [Index(value = ["name"], unique = true)])

data class Stat(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "value")
    val value: Float,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
