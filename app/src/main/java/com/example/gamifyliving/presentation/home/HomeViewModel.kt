package com.example.gamifyliving.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.use_case.ChangeTaskStatus
import com.example.gamifyliving.domain.use_case.GetTasks
import com.example.gamifyliving.domain.util.FilterTaskOn
import com.example.gamifyliving.domain.util.SortCriteria
import com.example.gamifyliving.domain.util.SortTasksBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTasks: GetTasks,
    private val changeTaskStatus: ChangeTaskStatus
) : ViewModel() {

    var listTasks =
        getTasks(
            filters = setOf(
                FilterTaskOn.WITH_TIME
            ),
            sorts = listOf(
                SortTasksBy(sortCriteria = SortCriteria.TIME),
            ),
            filterForDate = LocalDate.now()
        )

    var calendarTasks =
        getTasks(
            filters = setOf(
                FilterTaskOn.WITH_TIME
            ),
            sorts = listOf(
                SortTasksBy(sortCriteria = SortCriteria.TIME),
            ),
            filterForDate = LocalDate.now()
        )

    var view by mutableStateOf(HomeViewType.CALENDAR)
        private set

    var isViewDropDownExpanded by mutableStateOf(false)
        private set

    fun onViewDropDownExpandedChange(updatedValue: Boolean) {
        isViewDropDownExpanded = updatedValue
    }

    fun onViewDropDownDismiss() {
        isViewDropDownExpanded = false
    }

    fun onViewChange(updatedView: HomeViewType) {
        view = updatedView
    }

    fun onTaskStatusChange(task: Task) = viewModelScope.launch {
        changeTaskStatus(task)
    }

}
