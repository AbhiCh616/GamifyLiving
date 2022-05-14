package com.example.gamifyliving.presentation.tasks

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.use_case.ChangeTaskStatus
import com.example.gamifyliving.domain.use_case.GetTasks
import com.example.gamifyliving.domain.util.SortCriteria
import com.example.gamifyliving.domain.util.SortTasksBy
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    getTasks: GetTasks,
    private val changeTaskStatus: ChangeTaskStatus
) : ViewModel() {

    val tasks = getTasks(
        sorts = listOf(SortTasksBy(sortCriteria = SortCriteria.UNDONE))
    )

    fun onCheckboxClicked(task: Task) = viewModelScope.launch {
        Log.d("VALE", task.toString())
        changeTaskStatus(task)
    }

}
