package com.example.gamifyliving.presentation.add_task

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamifyliving.domain.model.Todo
import com.example.gamifyliving.domain.use_case.AddTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskViewModel @Inject constructor(
    private val addTask: AddTask
) : ViewModel() {

    var name by mutableStateOf("")
        private set

    fun onNameChange(newName: String) {
        name = newName
    }

    fun onSaveClicked() = viewModelScope.launch {
        val newTask = Todo(name = name)
        addTask(newTask)
    }

}
