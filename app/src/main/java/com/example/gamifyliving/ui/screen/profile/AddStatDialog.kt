package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.gamifyliving.R
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.viewmodel.ProfileViewModel
import kotlin.math.truncate

@ExperimentalComposeUiApi
@Composable
fun AddStatDialog(
    viewModel: ProfileViewModel,
    onClose: () -> Unit
) {
    var statName by remember { mutableStateOf("") }
    var statValue by remember { mutableStateOf(0F) }

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Scaffold(
            topBar = { AddStatTopAppBar(onClose = { onClose() }) {
                addStat(statName, statValue, viewModel)
                onClose()
            } }
        ) {
            Surface(color = MaterialTheme.colors.background) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextField(
                        value = statName,
                        onValueChange = {statName = it},
                        label = { Text(stringResource(id = R.string.statName)) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Slider(
                        value = statValue,
                        onValueChange = {statValue = it}
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${(statValue * 100).toInt()}%")
                }
            }
        }
    }
}

@Composable
fun AddStatTopAppBar(
    onClose: () -> Unit,
    onSave: () -> Unit
) {
    TopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { onClose() }) {
                Icon(Icons.Rounded.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onSave() }) {
                Text("Save")
            }
        }
    )
}

fun addStat(statName: String, statValue: Float, viewModel: ProfileViewModel) {
    if(statName != "") {
        viewModel.insertStat(Stat(statName, truncate(statValue * 100) / 100))
    }
}