package com.example.gamifyliving.data.data_source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "task")

data class TaskEntity(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "status")
    val status: Boolean,

    @ColumnInfo(name = "coins_reward")
    val coinsReward: Int,

    @ColumnInfo(name = "scheduled_date")
    val scheduledDate: LocalDate?,

    @ColumnInfo(name = "start_time")
    val startTime: LocalTime?,

    @ColumnInfo(name = "end_time")
    val endTime: LocalTime?,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)
