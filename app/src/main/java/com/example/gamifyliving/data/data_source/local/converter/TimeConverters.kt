package com.example.gamifyliving.data.data_source.local.converter

import androidx.room.TypeConverter
import java.time.LocalTime

class LocalTimeConverter {
    @TypeConverter
    fun fromTime(time: LocalTime?): String? {
        return time?.toString()
    }

    @TypeConverter
    fun toTime(timeString: String?): LocalTime? {
        return timeString?.let { LocalTime.parse(timeString) }
    }
}
