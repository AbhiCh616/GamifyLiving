package com.example.gamifyliving.presentation.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun getProgressFromStatValue(statValue: Int): Float =
    (statValue.toFloat() / (5 * 100))

fun getStatValueFromSliderValue(progress: Float): Int =
    (progress * 100 * 5).toInt()

fun Long?.toDateString(): String? =
    this?.let {
        val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.US)
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = it
        formatter.format(calendar.time)
    }

fun LocalDate?.toDateString(): String? =
    this?.let {
        "${it.dayOfMonth.toString().padStart(2, '0')}-" +
                "${it.monthValue.toString().padStart(2, '0')}-" +
                "${it.year}"
    }

fun String?.toLocalDate(): LocalDate? =
    this?.let {
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
        LocalDate.parse(it, formatter)
    }
