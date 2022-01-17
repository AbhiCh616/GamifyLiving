package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.gamifyliving.R
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@ExperimentalComposeUiApi
@Composable
fun AddTaskDialog(
    onClose: () -> Unit,
    onSave: (String) -> Unit
) {
    var taskName by remember { mutableStateOf("") }

    AddTaskDialogContent(
        onClose = onClose,
        onSave = {
            onSave(taskName)
            onClose()
        },
        taskName,
        onTaskNameChange = { taskName = it }
    )
}

@ExperimentalComposeUiApi
@Composable
fun AddTaskDialogContent(
    onClose: () -> Unit,
    onSave: () -> Unit,
    taskName: String,
    onTaskNameChange: (String) -> Unit,
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Scaffold(
            topBar = {
                AddTaskTopAppBar(onClose = onClose) {
                    onSave()
                    onClose()
                }
            }
        ) {
            AddTaskDialogContentBody(
                taskName,
                onTaskNameChange,
            )
        }
    }
}

@Composable
fun AddTaskDialogContentBody(
    taskName: String,
    onTaskNameChange: (String) -> Unit,
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = taskName,
                onValueChange = onTaskNameChange,
                label = { Text(stringResource(id = R.string.taskName)) }
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun AddTaskDialogContentBodyPreview() {

    GamifyLivingTheme {
        AddTaskDialogContentBody(
            taskName = "",
            onTaskNameChange = {},
        )
    }
}