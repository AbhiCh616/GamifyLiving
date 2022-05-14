package com.example.gamifyliving.presentation.tasks

import android.util.Log
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
import com.example.gamifyliving.presentation.util.TaskType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasks: GetTasks,
    private val changeTaskStatus: ChangeTaskStatus
) : ViewModel() {


    var isTasksViewDropDownExpanded by mutableStateOf(false)
        private set

    var taskView: TaskType? by mutableStateOf(null)
        private set

    var tasks = getTasks(
        sorts = listOf(SortTasksBy(sortCriteria = SortCriteria.UNDONE)),
        filters = taskView?.let {
            setOf(
                when (it) {
                    TaskType.TODO -> FilterTaskOn.TODO
                    TaskType.HABIT -> FilterTaskOn.HABIT
                }
            )
        }
    )
        private set

    fun onTaskViewChange(taskViewType: TaskType?) {
        this.taskView = taskViewType
    }

    fun onTasksViewDropdownExpandedChange(currentState: Boolean) {
        isTasksViewDropDownExpanded = !isTasksViewDropDownExpanded
    }

    fun onTasksViewDropDownDismiss() {
        isTasksViewDropDownExpanded = false
        tasks = getTasks(
            sorts = listOf(SortTasksBy(sortCriteria = SortCriteria.UNDONE)),
            filters = taskView?.let {
                setOf(
                    when (it) {
                        TaskType.TODO -> FilterTaskOn.TODO
                        TaskType.HABIT -> FilterTaskOn.HABIT
                    }
                )
            }
        )
    }

    fun onCheckboxClicked(task: Task) = viewModelScope.launch {
        Log.d("VALE", task.toString())
        changeTaskStatus(task)
    }

}
