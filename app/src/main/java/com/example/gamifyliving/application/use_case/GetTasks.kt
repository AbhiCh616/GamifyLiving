package com.example.gamifyliving.application.use_case

import com.example.gamifyliving.domain.model.*
import com.example.gamifyliving.application.repository.TaskRepository
import com.example.gamifyliving.application.util.FilterTaskOn
import com.example.gamifyliving.application.util.SortCriteria
import com.example.gamifyliving.application.util.SortOrder
import com.example.gamifyliving.application.util.SortTasksBy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(
        sorts: List<SortTasksBy>? = null,
        filters: Set<FilterTaskOn>? = null,
        filterForDate: LocalDate? = null,
    ): Flow<List<Task>> = repository.observeTasks().transform { list ->

        var updatedList = list

        filters?.forEach { filter ->
            updatedList = when (filter) {
                FilterTaskOn.TODO -> updatedList.filterIsInstance<Todo>()
                FilterTaskOn.HABIT -> updatedList.filterIsInstance<Habit>()
                FilterTaskOn.WITH_TIME -> updatedList.filter { it.schedule?.timeSpan != null }
            }
        }

        filterForDate?.let { filterDate ->
            updatedList = updatedList.filter { task ->
                when (task) {
                    is Todo -> task.schedule?.date == filterDate
                    is Habit -> when (task.schedule) {
                        is EverydaySchedule -> true
                        is RepeatAfterSchedule -> {
                            if ((task.schedule as RepeatAfterSchedule).startDate.isBefore(filterDate)) {
                                false
                            } else {
                                val diffOfDates = Period.between(
                                    (task.schedule as RepeatAfterSchedule).startDate,
                                    filterDate
                                ).days
                                if (diffOfDates == 0) {
                                    (task.schedule as RepeatAfterSchedule).interval == 0
                                } else {
                                    (diffOfDates % (task.schedule as RepeatAfterSchedule).interval).toInt() == 0
                                }
                            }
                        }
                        is WeekDaySchedule -> {
                            val today = Calendar.getInstance()
                            today.time = Date.from(
                                filterDate.atStartOfDay(ZoneId.systemDefault()).toInstant()
                            )
                            val todayWeekDay = today.get(Calendar.DAY_OF_WEEK)
                            (task.schedule as WeekDaySchedule).let {
                                when (todayWeekDay) {
                                    Calendar.SUNDAY -> it.sunday
                                    Calendar.MONDAY -> it.monday
                                    Calendar.TUESDAY -> it.tuesday
                                    Calendar.WEDNESDAY -> it.wednesday
                                    Calendar.THURSDAY -> it.thursday
                                    Calendar.FRIDAY -> it.friday
                                    Calendar.SATURDAY -> it.saturday
                                    else -> throw IllegalStateException()
                                }
                            }
                        }
                        null -> {
                            false
                        }
                        else -> {
                            throw IllegalStateException()
                        }
                    }
                    else -> {
                        throw IllegalStateException()
                    }
                }
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
