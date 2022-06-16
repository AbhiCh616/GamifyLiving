package com.example.gamifyliving.domain.use_case.query

import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.model.value_object.EverydaySchedule
import com.example.gamifyliving.domain.model.value_object.RepeatSchedule
import com.example.gamifyliving.domain.model.value_object.WeekDaySchedule
import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.repository.TodoRepository
import com.example.gamifyliving.domain.util.FilterTaskOn
import com.example.gamifyliving.domain.util.SortCriteria
import com.example.gamifyliving.domain.util.SortOrder
import com.example.gamifyliving.domain.util.SortTasksBy
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId
import java.util.*
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val todoRepository: TodoRepository,
    private val habitRepository: HabitRepository
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(
        sorts: List<SortTasksBy>? = null,
        filters: Set<FilterTaskOn>? = null,
        filterForDate: LocalDate? = null,
    ): Flow<List<Task>> = flowOf(todoRepository.observe(), habitRepository.observe()).flattenMerge()
        .transform { list ->

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
                            is RepeatSchedule -> {
                                if ((task.schedule as RepeatSchedule).startDate.isBefore(
                                        filterDate
                                    )
                                ) {
                                    false
                                } else {
                                    val diffOfDates = Period.between(
                                        (task.schedule as RepeatSchedule).startDate,
                                        filterDate
                                    ).days
                                    if (diffOfDates == 0) {
                                        (task.schedule as RepeatSchedule).interval == 0
                                    } else {
                                        (diffOfDates % (task.schedule as RepeatSchedule).interval).toInt() == 0
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
