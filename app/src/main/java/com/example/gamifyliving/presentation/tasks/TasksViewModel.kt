package com.example.gamifyliving.presentation.tasks

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.entity.Habit
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.domain.model.entity.Todo
import com.example.gamifyliving.domain.use_case.command.ChangeHabitStatus
import com.example.gamifyliving.domain.use_case.command.ChangeTodoStatus
import com.example.gamifyliving.domain.use_case.query.GetTasks
import com.example.gamifyliving.domain.util.TaskFilter
import com.example.gamifyliving.domain.util.TaskSortCriteria
import com.example.gamifyliving.domain.util.TaskSort
import com.example.gamifyliving.presentation.util.TaskType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasks: GetTasks,
    private val changeTodoStatus: ChangeTodoStatus,
    private val changeHabitStatus: ChangeHabitStatus
) : ViewModel() {


    var isTasksViewDropDownExpanded by mutableStateOf(false)
        private set

    var taskView: TaskType? by mutableStateOf(null)
        private set

    var tasks = getTasks(
        sorts = listOf(TaskSort(sortCriteria = TaskSortCriteria.NOT_DONE)),
        filters = taskView?.let {
            setOf(
                when (it) {
                    TaskType.TODO -> TaskFilter.TODO
                    TaskType.HABIT -> TaskFilter.HABIT
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
            sorts = listOf(TaskSort(sortCriteria = TaskSortCriteria.NOT_DONE)),
            filters = taskView?.let {
                setOf(
                    when (it) {
                        TaskType.TODO -> TaskFilter.TODO
                        TaskType.HABIT -> TaskFilter.HABIT
                    }
                )
            }
        )
    }

    fun onCheckboxClicked(task: Task) = viewModelScope.launch {
        Log.d("VALE", task.toString())
        if (task is Todo) {
            changeTodoStatus(task)
        }
        if (task is Habit) {
            changeHabitStatus(task)
        }
    }

}
