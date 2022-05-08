package com.example.gamifyliving.data.data_source.local.model

import androidx.room.*
import java.time.LocalDate

@Entity(
    tableName = "todo_schedule",
    foreignKeys = [
        ForeignKey(
            entity = TodoEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("todo_id"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["todo_id"], unique = true)],
)

data class TodoScheduleEntity(

    @ColumnInfo(name = "scheduled_date")
    val scheduledDate: LocalDate,

    @Embedded
    val timeSpan: TimeSpanEntity?,

    @ColumnInfo(name = "todo_id")
    val todoId: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int

)
