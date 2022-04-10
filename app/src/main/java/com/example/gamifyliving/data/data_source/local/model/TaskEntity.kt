package com.example.gamifyliving.data.data_source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Task")

data class TaskEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "status")
    val status: Boolean = false,

    @ColumnInfo(name = "coinsReward")
    val coinsReward: Int = 0,

    @ColumnInfo(name = "startDate")
    val startDate: LocalDate? = null,

    @ColumnInfo(name = "endDate")
    val endDate: LocalDate? = null,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
