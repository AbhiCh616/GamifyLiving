package com.example.gamifyliving.data.data_source.local.model.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "reward",
    primaryKeys = ["task_id", "stat_id"],
    indices = [Index(value = ["stat_id"])],
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
    val points: Int

)
