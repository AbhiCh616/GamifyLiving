package com.example.gamifyliving.domain.model

import androidx.room.*

@Entity(
    tableName = "Reward",
    indices = [Index(value = ["taskId", "statId"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = Task::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("taskId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Stat::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("statId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class Reward(
    @ColumnInfo(name = "taskId")
    val taskId: Int,

    @ColumnInfo(name = "statId")
    val statId: Int,

    @ColumnInfo(name = "points")
    val points: Int,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
