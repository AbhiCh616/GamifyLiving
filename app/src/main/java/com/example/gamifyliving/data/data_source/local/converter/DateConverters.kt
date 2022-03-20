package com.example.gamifyliving.data.data_source.local.converter

import androidx.room.TypeConverter
import java.time.LocalDate

class LocalDateConverter {
    @TypeConverter
    fun fromDate(date: LocalDate?): String? {
        return date?.toString()
    }

    @TypeConverter
    fun toDate(dateString: String?): LocalDate? {
        return dateString?.let { LocalDate.parse(dateString) }
    }
}
