package com.example.gamifyliving.data.data_source.local.model.table

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.gamifyliving.data.data_source.local.model.table.TaskEntity

@Entity(
    tableName = "todo",
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("task_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class TodoEntity(

    @ColumnInfo(name = "coins_reward")
    val coinsReward: Int,

    @PrimaryKey
    @ColumnInfo(name = "task_id")
    val taskId: Int

)
