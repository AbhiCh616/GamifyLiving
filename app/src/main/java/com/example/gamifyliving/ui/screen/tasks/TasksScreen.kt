package com.example.gamifyliving.ui.screen.tasks

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.ui.screen.component.TasksList

@ExperimentalMaterialApi
@Composable
fun TasksScreen(
    viewModel: TasksViewModel = viewModel(
        factory = TasksViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).taskRepository
        )
    ),
    onAddButtonClick: () -> Unit,
    onTaskClick: (Task) -> Unit
) {
    val tasks by viewModel.tasks.collectAsState(initial = emptyList())

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
                    onCheckboxClick = viewModel::changeTaskStatus,
                    onTaskClick = onTaskClick
                )
            }
        }
    }
}
