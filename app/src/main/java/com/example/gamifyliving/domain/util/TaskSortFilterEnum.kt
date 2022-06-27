package com.example.gamifyliving.domain.util

enum class TaskSortCriteria {
    TIME, NOT_DONE, WITH_TIME
}

enum class TaskSortOrder {
    ASC, DESC
}

data class TaskSort(
    val sortCriteria: TaskSortCriteria,
    val sortOrder: TaskSortOrder = TaskSortOrder.ASC
)

enum class TaskFilter {
    TODO, HABIT, WITH_TIME
}
