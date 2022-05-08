package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*

@Entity(
    tableName = "todo",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("task_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["task_id"], unique = true)],
)

data class TodoEntity(

    @ColumnInfo(name = "coins_reward")
    val coinsReward: Int,

    @ColumnInfo(name = "task_id")
    val taskId: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int
)
