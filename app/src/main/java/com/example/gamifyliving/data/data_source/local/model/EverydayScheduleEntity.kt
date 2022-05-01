package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*

@Entity(
    tableName = "everyday_schedule",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("habit_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["habit_id"], unique = true)],
)

data class EverydayScheduleEntity(

    @Embedded
    val timeSpan: TimeSpanEntity?,

    @ColumnInfo(name = "habit_id")
    val habitId: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0

)
