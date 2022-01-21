package com.example.gamifyliving.ui.screen.add_stat

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.util.getStatValueFromProgress
import com.example.gamifyliving.viewmodel.ProfileViewModel
import com.example.gamifyliving.viewmodel.ProfileViewModelFactory

@ExperimentalComposeUiApi
@Composable
fun AddStatDialog(
    onClose: () -> Unit,
    //onSave: (String, Float) -> Unit,
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).statRepository
        )
    )
) {
    var statName by remember { mutableStateOf("") }
    var statValue by remember { mutableStateOf(0F) }

    AddStatDialogContent(
        onClose = onClose,
        onSave = {
            if (statName != "") {
                viewModel.addStat(Stat(statName, getStatValueFromProgress(statValue)))
            }
        },
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
            AddStatDialogContentBody(
                statName = statName,
                statValue = statValue,
                onStatNameChange = onStatNameChange,
                onStatValueChange = onStatValueChange
            )
        }
    }
}

@Composable
fun AddStatDialogContentBody(
    statName: String,
    statValue: Float,
    onStatNameChange: (String) -> Unit,
    onStatValueChange: (Float) -> Unit
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

@ExperimentalComposeUiApi
@Preview
@Composable
fun AddStatDialogContentBodyPreview() {
    var statName by remember { mutableStateOf("") }
    var statValue by remember { mutableStateOf(0F) }

    GamifyLivingTheme {
        AddStatDialogContentBody(
            statName = statName,
            statValue = statValue,
            onStatNameChange = { statName = it },
            onStatValueChange = { statValue = it }
        )
    }
}

@Composable
fun AddStatTopAppBar(
    onClose: () -> Unit,
    onSave: () -> Unit
) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.addStat)) },
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
fun AddStatTopAppBarPreview() {
    GamifyLivingTheme {
        AddStatTopAppBar(onClose = { }) { }
    }
}