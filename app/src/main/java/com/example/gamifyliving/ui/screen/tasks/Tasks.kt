package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.data.model.Task
import com.example.gamifyliving.ui.component.BottomNavigationBar
import com.example.gamifyliving.ui.component.bottomNavigationItems
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.viewmodel.TasksViewModel
import com.example.gamifyliving.viewmodel.TasksViewModelFactory

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun Tasks(
    navController: NavController,
    viewModel: TasksViewModel = viewModel(
        factory = TasksViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).taskRepository
        )
    )
) {
    val tasks by viewModel.tasks.observeAsState(emptyList())

    var isEditTaskDialogVisible by remember { mutableStateOf(false) }
    var selectedTask: Task? by remember { mutableStateOf(null) }

    TasksContent(
        tasks,
        navController,
        changeTaskStatus = { viewModel.changeTaskStatus(it) },
        onIndividualTaskClick = {
            selectedTask = it
            isEditTaskDialogVisible = true
        },
        isEditTaskDialogVisible = isEditTaskDialogVisible,
        hideEditTaskDialog = { isEditTaskDialogVisible = false },
        editTask = { task, taskName ->
            if (taskName != "") {
                viewModel.updateTaskValues(task, taskName)
            }
        },
        selectedTask = selectedTask,
        onTaskDelete = {
            viewModel.deleteTask(it)
        }
    )
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun TasksContent(
    tasks: List<Task>,
    navController: NavController,
    changeTaskStatus: (Task) -> Unit,
    onIndividualTaskClick: (Task) -> Unit,
    isEditTaskDialogVisible: Boolean,
    hideEditTaskDialog: () -> Unit,
    editTask: (Task, String) -> Unit,
    selectedTask: Task?,
    onTaskDelete: (Task) -> Unit
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavigationItems,
                navController = navController
            )
        }
    ) {
        TasksContentBody(
            tasks,
            changeTaskStatus,
            onIndividualTaskClick,
            isEditTaskDialogVisible,
            hideEditTaskDialog,
            editTask,
            selectedTask,
            onTaskDelete
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun TasksContentBody(
    tasks: List<Task>,
    changeTaskStatus: (Task) -> Unit,
    onIndividualTaskClick: (Task) -> Unit,
    isEditTaskDialogVisible: Boolean,
    hideEditTaskDialog: () -> Unit,
    editTask: (Task, String) -> Unit,
    selectedTask: Task?,
    onTaskDelete: (Task) -> Unit
) {
    if (isEditTaskDialogVisible && selectedTask != null) {
        EditTaskDialog(
            task = selectedTask,
            onClose = hideEditTaskDialog,
            onSave = editTask,
            onTaskDelete = onTaskDelete
        )
    } else {
        TasksMainContent(
            tasks,
            changeTaskStatus,
            onIndividualTaskClick,
        )
    }
}

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Preview
@Composable
fun TasksMainContentPreview() {
    val tasks = listOf(Task("abc"), Task("xyz"))

    GamifyLivingTheme {
        TasksContentBody(tasks, {}, {}, false, {}, { Task, String -> }, null, {})
    }
}