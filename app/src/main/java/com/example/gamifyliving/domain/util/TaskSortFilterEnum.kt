package com.example.gamifyliving.domain.util

enum class SortCriteria {
    TIME, UNDONE, WITHOUT_TIME
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
