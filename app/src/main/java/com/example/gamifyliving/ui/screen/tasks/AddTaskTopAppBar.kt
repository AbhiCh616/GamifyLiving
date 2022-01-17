package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamifyliving.R
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@Composable
fun AddTaskTopAppBar(
    onClose: () -> Unit,
    onSave: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.addTask)) },
        navigationIcon = {
            IconButton(onClick = onClose) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            TextButton(onClick = onSave) {
                Text(stringResource(id = R.string.create))
            }
        }
    )
}

@Preview
@Composable
fun AddTaskTopAppBarPreview() {
    GamifyLivingTheme {
        AddTaskTopAppBar(onClose = { }) { }
    }
}