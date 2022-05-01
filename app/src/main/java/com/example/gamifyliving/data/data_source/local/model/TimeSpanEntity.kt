package com.example.gamifyliving.data.data_source.local.model

import androidx.room.ColumnInfo
import java.time.LocalTime

data class TimeSpanEntity(

    @ColumnInfo(name = "start_time")
    val startTime: LocalTime,

    @ColumnInfo(name = "end_time")
    val endTime: LocalTime

)
