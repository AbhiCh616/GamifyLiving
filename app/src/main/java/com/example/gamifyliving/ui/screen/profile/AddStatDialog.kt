package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.gamifyliving.R

@ExperimentalComposeUiApi
@Composable
fun AddStatDialog(
    onClose: () -> Unit,
    onSave: (String, Float) -> Unit
) {
    var statName by remember { mutableStateOf("") }
    var statValue by remember { mutableStateOf(0F) }

    AddStatDialogContent(
        onClose = onClose,
        onSave = { onSave(statName, statValue) },
        statName = statName,
        statValue = statValue,
        onStatNameChange = { statName = it },
        onStatValueChange = { statValue = it }
    )

}

@ExperimentalComposeUiApi
@Composable
fun AddStatDialogContent(
    onClose: () -> Unit,
    onSave: () -> Unit,
    statName: String,
    statValue: Float,
    onStatNameChange: (String) -> Unit,
    onStatValueChange: (Float) -> Unit
) {
    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Scaffold(
            topBar = {
                AddStatTopAppBar(onClose = onClose) {
                    onSave()
                    onClose()
                }
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
                        value = statName,
                        onValueChange = onStatNameChange,
                        label = { Text(stringResource(id = R.string.statName)) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Slider(
                        value = statValue,
                        onValueChange = onStatValueChange
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "${(statValue * 100).toInt()}%")
                }
            }
        }
    }
}