package com.example.gamifyliving.util

fun getProgressFromStatValue(statValue: Int): Float {
    return (statValue.toFloat() / (5 * 100))
}

fun getStatValueFromProgress(progress: Float): Int {
    return (progress * 100 * 5).toInt()
}