package com.example.gamifyliving.ui.screen.profile

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
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import java.nio.file.Files.delete

@ExperimentalComposeUiApi
@Composable
fun EditStatDialog(
    stat: Stat,
    onClose: () -> Unit,
    onSave: (Stat, String, Float) -> Unit,
    onStatDelete: (Stat) -> Unit
) {
    var statName by remember { mutableStateOf(stat.name) }
    var statValue by remember { mutableStateOf(stat.value) }
    
    EditStatDialogContent(
        onClose = onClose,
        onSave = { onSave(stat, statName, statValue) },
        onStatDelete = {
            onStatDelete(stat)
            onClose()
        },
        statName = statName,
        statValue = statValue,
        onStatNameChange = { statName = it },
        onStatValueChange = { statValue = it }
    )
}

@ExperimentalComposeUiApi
@Composable
fun EditStatDialogContent(
    onClose: () -> Unit,
    onSave: () -> Unit,
    onStatDelete: () -> Unit,
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
                EditStatTopAppBar(onClose = onClose) {
                    onSave()
                    onClose() 
                } 
            }
        ) {
            EditStatDialogContentBody(
                statName = statName,
                statValue = statValue,
                onStatNameChange = onStatNameChange,
                onStatValueChange = onStatValueChange,
                onStatDelete = onStatDelete
            )
        }
    }
}

@Composable
fun EditStatDialogContentBody(
    statName: String, 
    statValue: Float, 
    onStatNameChange: (String) -> Unit, 
    onStatValueChange: (Float) -> Unit,
    onStatDelete: () -> Unit
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
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(onClick = onStatDelete) {
                Text(stringResource(id = R.string.delete))
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun EditStatDialogContentBodyPreview() {
    GamifyLivingTheme {
        EditStatDialogContentBody(
            statName = "",
            statValue = 0F,
            onStatNameChange = {},
            onStatValueChange = {},
            onStatDelete = {}
        )
    }
}
