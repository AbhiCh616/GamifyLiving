package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*

@Entity(
    tableName = "habit",
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

data class HabitEntity(

    @ColumnInfo(name = "task_id")
    val taskId: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)
