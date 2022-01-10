package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.data.model.Task
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@Composable
fun TasksList(
    tasks: List<Task>
) {
    LazyColumn {
        items(tasks) {
            IndividualTask(
                it,
                Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun TasksListPreview() {
    val tasks = listOf(Task("abc"), Task("xyz 123"))

    GamifyLivingTheme() {
        TasksList(tasks)
    }
}