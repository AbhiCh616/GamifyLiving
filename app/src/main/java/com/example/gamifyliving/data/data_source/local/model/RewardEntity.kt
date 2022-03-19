package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*

@Entity(
    tableName = "Reward",
    indices = [Index(value = ["taskId", "statId"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("taskId"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StatEntity::class,
            parentColumns = arrayOf("uid"),
            childColumns = arrayOf("statId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class RewardEntity(
    @ColumnInfo(name = "taskId")
    val taskId: Int,

    @ColumnInfo(name = "statId")
    val statId: Int,

    @ColumnInfo(name = "points")
    val points: Int,

    @PrimaryKey(autoGenerate = true)
    val uid: Int = 0
)
