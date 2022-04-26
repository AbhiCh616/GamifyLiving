package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*

@Entity(
    tableName = "reward",
    indices = [Index(value = ["task_id", "stat_id"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("task_id"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = StatEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("stat_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class RewardEntity(
    @ColumnInfo(name = "task_id")
    val taskId: Int,

    @ColumnInfo(name = "stat_id")
    val statId: Int,

    @ColumnInfo(name = "points")
    val points: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)
