package com.example.gamifyliving.data.data_source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "Task")

data class TaskEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "status")
    val status: Boolean = false,

    @ColumnInfo(name = "coinsReward")
    val coinsReward: Int = 0,

    @ColumnInfo(name = "scheduledDate")
    val scheduledDate: LocalDate? = null,

    @ColumnInfo(name = "startTime")
    val startTime: LocalTime? = null,

    @ColumnInfo(name = "endTime")
    val endTime: LocalTime? = null,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
