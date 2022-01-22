package com.example.gamifyliving.presentation.edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.R

@Composable
fun EditTaskHandler(
    onClose: () -> Unit
) {
    EditTask(
        name = "",
        onNameChange = {},
        onDelete = {},
        onClose = {},
        onSave = {}
    )
}

@Composable
fun EditTask(
    name: String,
    onNameChange: (String) -> Unit,
    onDelete: () -> Unit,
    onClose: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.editTask)) },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    TextButton(onClick = onSave) {
                        Text(stringResource(id = R.string.save))
                    }
                }
            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = name,
                    onValueChange = onNameChange,
                    label = { Text(stringResource(id = R.string.taskName)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = onDelete) {
                    Text(stringResource(id = R.string.delete))
                }
            }
        }
    }
}