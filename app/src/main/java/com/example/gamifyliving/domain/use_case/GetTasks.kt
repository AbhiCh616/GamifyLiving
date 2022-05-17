package com.example.gamifyliving.domain.use_case

import com.example.gamifyliving.domain.model.Habit
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.model.Todo
import com.example.gamifyliving.domain.repository.TaskRepository
import com.example.gamifyliving.domain.util.FilterTaskOn
import com.example.gamifyliving.domain.util.SortCriteria
import com.example.gamifyliving.domain.util.SortOrder
import com.example.gamifyliving.domain.util.SortTasksBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(
        sorts: List<SortTasksBy>? = null,
        filters: Set<FilterTaskOn>? = null
    ): Flow<List<Task>> = repository.observeTasks().transform { list ->

        var updatedList = list

        filters?.forEach { filter ->
            updatedList = when (filter) {
                FilterTaskOn.TODO -> updatedList.filterIsInstance<Todo>()
                FilterTaskOn.HABIT -> updatedList.filterIsInstance<Habit>()
                FilterTaskOn.WITH_TIME -> updatedList.filter { it.schedule?.timeSpan != null }
            }
        }

        sorts?.forEach { sort ->
            updatedList = when (sort.sortCriteria) {
                SortCriteria.TIME -> updatedList.sortedBy { it.schedule?.timeSpan?.startTime }
                SortCriteria.UNDONE -> updatedList.sortedBy { it.status }
                SortCriteria.WITH_TIME -> updatedList.sortedBy { it.schedule?.timeSpan == null }
            }
            if (sort.sortOrder == SortOrder.DESC) {
                updatedList = updatedList.reversed()
            }
        }

        emit(
            updatedList
        )

    }

}
