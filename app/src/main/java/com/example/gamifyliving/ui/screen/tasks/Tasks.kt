package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.ui.screen.add_task.AddTaskDialog
import com.example.gamifyliving.ui.screen.edit_task.EditTaskDialog
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
            (LocalContext.current.applicationContext as GamifyLivingApplication).taskRepository,
            (LocalContext.current.applicationContext as GamifyLivingApplication).rewardRepository
        )
    )
) {
    val tasks by viewModel.getAllTasks().observeAsState(emptyList())

    var isEditTaskDialogVisible by remember { mutableStateOf(false) }
    var selectedTask: Task? by remember { mutableStateOf(null) }
    var isAddTaskDialogVisible by remember { mutableStateOf(false) }

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
                viewModel.updateTaskName(task, taskName)
            }
        },
        selectedTask = selectedTask,
        onTaskDelete = {
            viewModel.deleteTask(it)
        },
        isAddTaskDialogVisible = isAddTaskDialogVisible,
        showAddTaskDialog = { isAddTaskDialogVisible = true },
        hideAddTaskDialog = { isAddTaskDialogVisible = false },
        createNewTask = { taskName ->
            if (taskName != "") {
                viewModel.addTask(Task(taskName))
            }
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
    onTaskDelete: (Task) -> Unit,
    isAddTaskDialogVisible: Boolean,
    showAddTaskDialog: () -> Unit,
    hideAddTaskDialog: () -> Unit,
    createNewTask: (String) -> Unit
) {
    Scaffold(
        floatingActionButton = { AddStatFAB(showAddStatDialog = showAddTaskDialog) },
    ) {
        TasksContentBody(
            tasks,
            changeTaskStatus,
            onIndividualTaskClick,
            isEditTaskDialogVisible,
            hideEditTaskDialog,
            editTask,
            selectedTask,
            onTaskDelete,
            isAddTaskDialogVisible,
            hideAddTaskDialog,
            createNewTask,
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
    onTaskDelete: (Task) -> Unit,
    isAddTaskDialogVisible: Boolean,
    hideAddTaskDialog: () -> Unit,
    createNewTask: (String) -> Unit
) {
    if (isEditTaskDialogVisible && selectedTask != null) {
        EditTaskDialog(
            task = selectedTask,
            onClose = hideEditTaskDialog,
            onSave = editTask,
            onTaskDelete = onTaskDelete
        )
    } else if (isAddTaskDialogVisible) {
        AddTaskDialog(onClose = hideAddTaskDialog, onSave = createNewTask)
    } else {
        TasksMainContent(
            tasks,
            changeTaskStatus,
            onIndividualTaskClick
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
        TasksContentBody(
            tasks, {}, {}, false,
            {}, { _, _ -> }, null,
            {}, false, {}, {})
    }
}

@Composable
fun AddStatFAB(
    showAddStatDialog: () -> Unit
) {
    FloatingActionButton(onClick = showAddStatDialog) {
        Icon(Icons.Rounded.Add, null)
    }
}