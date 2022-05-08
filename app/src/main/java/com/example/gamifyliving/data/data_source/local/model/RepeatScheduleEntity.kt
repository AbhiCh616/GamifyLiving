package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*
import java.time.LocalDate

@Entity(
    tableName = "repeat_after_schedule",
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

data class RepeatScheduleEntity(

    @ColumnInfo(name = "start_date")
    val startDate: LocalDate,

    @ColumnInfo(name = "repeat_after")
    val repeatAfter: Int,

    @Embedded
    val timeSpan: TimeSpanEntity?,

    @ColumnInfo(name = "habit_id")
    val habitId: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int

)
