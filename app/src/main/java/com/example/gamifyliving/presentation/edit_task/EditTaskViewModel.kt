package com.example.gamifyliving.presentation.edit_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.use_case.DeleteTask
import com.example.gamifyliving.domain.use_case.GetTaskById
import com.example.gamifyliving.domain.use_case.UpdateTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTaskViewModel @Inject constructor(
    private val getTaskById: GetTaskById,
    private val deleteTask: DeleteTask,
    private val updateTask: UpdateTask,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    private var selectedTask: Task? = null

    init {
        savedStateHandle.get<Int>("task_id")?.let { taskId ->
            viewModelScope.launch {
                getTaskById(taskId)?.let { task ->
                    selectedTask = task
                    name = task.name
                }
            }
        }
    }

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onDeleteClicked() = viewModelScope.launch {
        selectedTask?.let { deleteTask(it) }
    }

    fun onSaveClicked() = viewModelScope.launch {
        val updatedTask = selectedTask?.copy(name = name)
        updatedTask?.let { updateTask(it) }
    }

}
