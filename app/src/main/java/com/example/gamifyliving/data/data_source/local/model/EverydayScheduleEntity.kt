package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*

@Entity(
    tableName = "everyday_schedule",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = arrayOf("task_id"),
            childColumns = arrayOf("habit_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class EverydayScheduleEntity(

    @Embedded
    val timeSpan: TimeSpanEntity?,

    @PrimaryKey
    @ColumnInfo(name = "habit_id")
    val habitId: Int

)
