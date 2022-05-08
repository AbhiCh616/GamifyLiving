package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*

@Entity(
    tableName = "week_day_schedule",
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

data class WeekDayScheduleEntity(

    @ColumnInfo(name = "sunday")
    val sunday: Boolean,

    @ColumnInfo(name = "monday")
    val monday: Boolean,

    @ColumnInfo(name = "tuesday")
    val tuesday: Boolean,

    @ColumnInfo(name = "wednesday")
    val wednesday: Boolean,

    @ColumnInfo(name = "thursday")
    val thursday: Boolean,

    @ColumnInfo(name = "friday")
    val friday: Boolean,

    @ColumnInfo(name = "saturday")
    val saturday: Boolean,

    @Embedded
    val timeSpan: TimeSpanEntity?,

    @ColumnInfo(name = "habit_id")
    val habitId: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int

)
