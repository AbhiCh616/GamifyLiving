package com.example.gamifyliving.presentation.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.model.Todo
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme

@ExperimentalMaterialApi
@Composable
fun TasksScreenHandler(
    onAddButtonClick: () -> Unit,
    onTaskClick: (Task) -> Unit,
    viewModel: TasksViewModel = hiltViewModel()
) {

    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

    TasksScreen(
        tasks = tasks,
        changeTaskStatus = viewModel::onCheckboxClicked,
        onAddButtonClick = onAddButtonClick,
        onTaskClick = onTaskClick
    )

}

@ExperimentalMaterialApi
@Composable
fun TasksScreen(
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
                Text(
                    text = stringResource(id = R.string.tasks),
                    style = MaterialTheme.typography.h5
                )
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

@ExperimentalMaterialApi
@Preview
@Composable
fun TasksScreenPreview() {

    val tasks = listOf(
        Todo(name = "Do this"),
        Todo(name = "Do that")
    )

    GamifyLivingTheme {

        TasksScreen(
            tasks = tasks,
            changeTaskStatus = {},
            onAddButtonClick = {},
            onTaskClick = {}
        )

    }

}
