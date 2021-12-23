package com.example.gamifyliving.ui.screen.profile

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@ExperimentalComposeUiApi
@Composable
fun AddStatDialog(
    onClose: () -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Scaffold(
            topBar = { AddStatTopAppBar { onClose() } }
        ) {

        }
    }
}

@Composable
fun AddStatTopAppBar(onClose: () -> Unit) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { onClose() }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { /* doSomething() */ }) {
                Text("Save")
            }
        }
    )
}