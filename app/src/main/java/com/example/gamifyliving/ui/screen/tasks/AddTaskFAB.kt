package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@Composable
fun AddTaskFAB(
    showAddTaskDialog: () -> Unit
) {
    FloatingActionButton(onClick = showAddTaskDialog) {
        Icon(Icons.Rounded.Add, null)
    }
}

@Preview
@Composable
fun AddStatFABPreview() {
    GamifyLivingTheme {
        AddTaskFAB {}
    }
}