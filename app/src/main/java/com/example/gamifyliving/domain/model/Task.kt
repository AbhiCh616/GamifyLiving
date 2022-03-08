package com.example.gamifyliving.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Task")

data class Task(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "status")
    val status: Boolean = false,

    @ColumnInfo(name = "coinsReward")
    val coinsReward: Int = 0,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
