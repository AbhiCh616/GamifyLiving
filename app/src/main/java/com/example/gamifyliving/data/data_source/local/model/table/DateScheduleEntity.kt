package com.example.gamifyliving.data.data_source.local.model.table

import androidx.room.*
import com.example.gamifyliving.data.data_source.local.model.embedded.TimeSpanEntity
import java.time.LocalDate

@Entity(
    tableName = "date_schedule",
    foreignKeys = [
        ForeignKey(
            entity = TodoEntity::class,
            parentColumns = arrayOf("task_id"),
            childColumns = arrayOf("todo_id"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class DateScheduleEntity(

    @ColumnInfo(name = "scheduled_date")
    val scheduledDate: LocalDate,

    @Embedded
    val timeSpan: TimeSpanEntity?,

    @PrimaryKey
    @ColumnInfo(name = "todo_id")
    val todoId: Int

)
