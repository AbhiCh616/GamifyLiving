package com.example.gamifyliving.presentation.tasks

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.entity.Task
import com.example.gamifyliving.presentation.util.TaskType

@ExperimentalMaterialApi
@Composable
fun TasksScreenHandler(
    onAddButtonClick: () -> Unit,
    onTaskClick: (Task) -> Unit,
    viewModel: TasksViewModel = hiltViewModel()
) {

    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    TasksScreen(
        isTasksViewDropDownExpanded = viewModel.isTasksViewDropDownExpanded,
        onTasksViewDropDownExpandedChange = viewModel::onTasksViewDropdownExpandedChange,
        onTasksViewDropDownDismiss = viewModel::onTasksViewDropDownDismiss,
        taskView = viewModel.taskView,
        onTaskViewChange = viewModel::onTaskViewChange,
        tasks = tasks,
        changeTaskStatus = viewModel::onCheckboxClicked,
        onAddButtonClick = onAddButtonClick,
        onTaskClick = onTaskClick
    )

}

@ExperimentalMaterialApi
@Composable
fun TasksScreen(
    isTasksViewDropDownExpanded: Boolean,
    onTasksViewDropDownExpandedChange: (Boolean) -> Unit,
    onTasksViewDropDownDismiss: () -> Unit,
    taskView: TaskType?,
    onTaskViewChange: (TaskType?) -> Unit,
    tasks: List<Task>,
    changeTaskStatus: (Task) -> Unit,
    onAddButtonClick: () -> Unit,
    onTaskClick: (Task) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddButtonClick) {
                Icon(Icons.Rounded.Add, null)
            }
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.tasks),
                        style = MaterialTheme.typography.h5
                    )
                    TasksViewDropDown(
                        expanded = isTasksViewDropDownExpanded,
                        onExpandedChange = onTasksViewDropDownExpandedChange,
                        onDismiss = onTasksViewDropDownDismiss,
                        taskType = taskView,
                        onTaskViewChange = onTaskViewChange
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                TasksList(
                    tasks = tasks,
                    onCheckboxClick = changeTaskStatus,
                    onTaskClick = onTaskClick
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TasksViewDropDown(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    taskType: TaskType?,
    onTaskViewChange: (TaskType?) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = Modifier.width(120.dp)
    ) {
        TextField(
            readOnly = true,
            value = stringResource(
                id =
                when (taskType) {
                    TaskType.TODO -> R.string.todos
                    TaskType.HABIT -> R.string.habits
                    null -> R.string.tasks
                }
            ),
            onValueChange = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss
        ) {
            DropdownMenuItem(
                onClick = {
                    onTaskViewChange(null)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.tasks))
            }
            DropdownMenuItem(
                onClick = {
                    onTaskViewChange(TaskType.TODO)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.todos))
            }
            DropdownMenuItem(
                onClick = {
                    onTaskViewChange(TaskType.HABIT)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.habits))
            }
        }
    }
}
