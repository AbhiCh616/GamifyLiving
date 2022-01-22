package com.example.gamifyliving.presentation.edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.R

@Composable
fun EditTaskHandler(
    onClose: () -> Unit,
    viewModel: EditTaskViewModel =
        (LocalContext.current.applicationContext as GamifyLivingApplication)
            .editTaskViewModelFactory
            .create(EditTaskViewModel::class.java)
) {
    EditTask(
        name = viewModel.name,
        onNameChange = viewModel::onNameChange,
        onDelete = viewModel::onDelete,
        onClose = onClose,
        onSave = {
            viewModel.onSaveClicked()
            onClose()
        }
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