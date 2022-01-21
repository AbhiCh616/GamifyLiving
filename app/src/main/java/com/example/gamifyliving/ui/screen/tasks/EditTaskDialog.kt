package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.foundation.layout.*
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
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@ExperimentalComposeUiApi
@Composable
fun EditTaskDialog(
    task: Task,
    onClose: () -> Unit,
    onSave: (Task, String) -> Unit,
    onTaskDelete: (Task) -> Unit
) {
    var taskName by remember { mutableStateOf(task.name) }

    EditTaskDialogContent(
        onClose,
        onSave = {
            onSave(task, taskName)
            onClose()
        },
        onTaskDelete = {
            onTaskDelete(task)
            onClose()
        },
        taskName,
        onTaskNameChange = { taskName = it }
    )
}

@ExperimentalComposeUiApi
@Composable
fun EditTaskDialogContent(
    onClose: () -> Unit,
    onSave: () -> Unit,
    onTaskDelete: () -> Unit,
    taskName: String,
    onTaskNameChange: (String) -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Scaffold(
            topBar = {
                EditTaskTopAppBar(onClose = onClose) {
                    onSave()
                    onClose()
                }
            }
        ) {
            EditTaskDialogContentBody(taskName, onTaskNameChange, onTaskDelete)
        }
    }
}

@Composable
fun EditTaskDialogContentBody(
    taskName: String,
    onTaskNameChange: (String) -> Unit,
    onTaskDelete: () -> Unit,
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
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = onTaskDelete) {
                Text(stringResource(id = R.string.delete))
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun EditTaskDialogContentBodyPreview() {
    GamifyLivingTheme {
        EditTaskDialogContentBody(
            taskName = "",
            onTaskNameChange = {},
            onTaskDelete = {},
        )
    }
}