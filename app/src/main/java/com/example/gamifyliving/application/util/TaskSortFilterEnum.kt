package com.example.gamifyliving.application.util

enum class SortCriteria {
    TIME, UNDONE, WITH_TIME
}

enum class SortOrder {
    ASC, DESC
}

data class SortTasksBy(
    val sortCriteria: SortCriteria,
    val sortOrder: SortOrder = SortOrder.ASC
)

enum class FilterTaskOn {
    TODO, HABIT, WITH_TIME
}
