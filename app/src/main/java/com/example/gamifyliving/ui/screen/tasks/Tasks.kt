package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.R
import com.example.gamifyliving.data.model.Task
import com.example.gamifyliving.ui.component.BottomNavigationBar
import com.example.gamifyliving.ui.component.bottomNavigationItems
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.viewmodel.TasksViewModel
import com.example.gamifyliving.viewmodel.TasksViewModelFactory

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

    TasksContent(tasks, navController)
}

@Composable
fun TasksContent(tasks: List<Task>, navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavigationItems,
                navController = navController
            )
        }
    ) {
        TasksMainContent(tasks)
    }
}

@Composable
fun TasksMainContent(tasks: List<Task>) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(id = R.string.tasks), style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            TasksList(tasks)
        }
    }
}

@Preview
@Composable
fun TasksMainContentPreview() {
    val tasks = listOf(Task("abc"), Task("xyz"))

    GamifyLivingTheme {
        TasksMainContent(tasks)
    }
}