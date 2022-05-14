package com.example.gamifyliving.presentation.tasks

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.domain.model.Todo
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TasksList(
    tasks: List<Task>,
    onCheckboxClick: (Task) -> Unit,
    onTaskClick: (Task) -> Unit,
) {

    LazyColumn {
        items(tasks) { task ->
            TaskCard(
                task = task,
                modifier = Modifier.padding(vertical = 8.dp),
                onCheckboxClick = onCheckboxClick,
                onClick = onTaskClick
            )
        }
    }

}

@ExperimentalMaterialApi
@Preview
@Composable
fun TasksListPreview() {

    val tasks = listOf(Todo(name = "abc"), Todo(name = "xyz 123"))

    GamifyLivingTheme {

        TasksList(
            tasks = tasks,
            onCheckboxClick = {},
            onTaskClick = {}
        )

    }

}
