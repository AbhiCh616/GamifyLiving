package com.example.gamifyliving.domain.use_case.query

import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.model.value_object.EverydaySchedule
import com.example.gamifyliving.domain.model.value_object.RepeatSchedule
import com.example.gamifyliving.domain.model.value_object.WeekDaySchedule
import com.example.gamifyliving.domain.repository.HabitRepository
import com.example.gamifyliving.domain.repository.TodoRepository
import com.example.gamifyliving.domain.util.TaskFilter
import com.example.gamifyliving.domain.util.TaskSort
import com.example.gamifyliving.domain.util.TaskSortCriteria
import com.example.gamifyliving.domain.util.TaskSortOrder
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flattenMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class GetTasks @Inject constructor(
    private val todoRepository: TodoRepository,
    private val habitRepository: HabitRepository
) {
    @OptIn(FlowPreview::class)
    operator fun invoke(
        sorts: List<TaskSort>? = null,
        filters: Set<TaskFilter>? = null,
        filterOnDate: LocalDate? = null,
    ) =

        flowOf(todoRepository.observe(), habitRepository.observe())
            .flattenMerge()
            .transform { tasks ->

                var filteredAndSortedTasks = tasks

                filters?.forEach { filter ->
                    filteredAndSortedTasks =
                        applyFilter(tasks = filteredAndSortedTasks, filter = filter)
                }

                filterOnDate?.let { filterDate ->
                    filteredAndSortedTasks =
                        applyDateFilter(tasks = filteredAndSortedTasks, date = filterDate)
                }

                sorts?.forEach { sort ->
                    filteredAndSortedTasks = applySort(tasks = filteredAndSortedTasks, sort = sort)
                }

                emit(
                    filteredAndSortedTasks
                )

            }

    private fun applyFilter(tasks: List<Task>, filter: TaskFilter) =
        when (filter) {
            TaskFilter.TODO -> tasks.filterIsInstance<Todo>()
            TaskFilter.HABIT -> tasks.filterIsInstance<Habit>()
            TaskFilter.WITH_TIME -> tasks.filter { it.schedule?.timeSpan != null }
        }

    private fun applySort(tasks: List<Task>, sort: TaskSort): List<Task> {
        val sortedTasks = when (sort.sortCriteria) {
            TaskSortCriteria.TIME -> tasks.sortedBy { it.schedule?.timeSpan?.startTime }
            TaskSortCriteria.NOT_DONE -> tasks.sortedBy { it.status }
            TaskSortCriteria.WITH_TIME -> tasks.sortedBy { it.schedule?.timeSpan == null }
        }
        if (sort.sortOrder == TaskSortOrder.DESC) {
            return sortedTasks.reversed()
        }
        return sortedTasks
    }

    private fun applyDateFilter(tasks: List<Task>, date: LocalDate) =
        tasks.filter { task ->
            when (task) {
                is Todo -> task.schedule?.date == date
                is Habit -> when (task.schedule) {
                    is EverydaySchedule -> true
                    is RepeatSchedule -> {
                        isRepeatScheduleOnDate(task.schedule as RepeatSchedule, date)
                    }
                    is WeekDaySchedule -> {
                        isWeekDayScheduleOnDate(task.schedule as WeekDaySchedule, date)
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

    private fun isRepeatScheduleOnDate(schedule: RepeatSchedule, date: LocalDate): Boolean {
        if (schedule.startDate.isBefore(date)) {
            return false
        }

        val diffInDates = ChronoUnit.DAYS.between(schedule.startDate, date).toInt()

        if (diffInDates == 0) {
            return true
        }

        return diffInDates % schedule.interval == 0
    }

    private fun isWeekDayScheduleOnDate(schedule: WeekDaySchedule, date: LocalDate) =
        when (date.dayOfWeek) {
            DayOfWeek.SUNDAY -> schedule.sunday
            DayOfWeek.MONDAY -> schedule.monday
            DayOfWeek.TUESDAY -> schedule.tuesday
            DayOfWeek.WEDNESDAY -> schedule.wednesday
            DayOfWeek.THURSDAY -> schedule.thursday
            DayOfWeek.FRIDAY -> schedule.friday
            DayOfWeek.SATURDAY -> schedule.saturday
            null -> throw IllegalStateException()
        }

}
