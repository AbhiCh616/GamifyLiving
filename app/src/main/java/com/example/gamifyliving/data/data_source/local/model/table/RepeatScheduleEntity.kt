package com.example.gamifyliving.data.data_source.local.model.table

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.embedded.TimeSpanEntity
import java.time.LocalDate

@Entity(
    tableName = "repeat_schedule",
    foreignKeys = [
        ForeignKey(
            entity = HabitEntity::class,
            parentColumns = arrayOf("task_id"),
            childColumns = arrayOf("habit_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class RepeatScheduleEntity(

    @ColumnInfo(name = "start_date")
    val startDate: LocalDate,

    @ColumnInfo(name = "repeat_after")
    val repeatAfter: Int,

    @Embedded
    val timeSpan: TimeSpanEntity?,

    @PrimaryKey
    @ColumnInfo(name = "habit_id")
    val habitId: Int

)
