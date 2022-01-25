package com.example.gamifyliving.presentation.add_stat

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme

@Composable
fun AddStatHandler(
    onClose: () -> Unit,
    viewModel: AddStatViewModel = hiltViewModel()
) {

    AddStat(
        name = viewModel.name,
        value = viewModel.value,
        onNameChange = viewModel::onNameChange,
        onValueChange = viewModel::onValueChange,
        onClose = onClose,
        onSave = {
            viewModel.onSaveClicked()
            onClose()
        }
    )

}

@Composable
fun AddStat(
    name: String,
    value: Float,
    onNameChange: (String) -> Unit,
    onValueChange: (Float) -> Unit,
    onClose: () -> Unit,
    onSave: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.addStat)) },
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
                    label = { Text(stringResource(id = R.string.statName)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Slider(
                    value = value,
                    onValueChange = onValueChange
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "${(value * 100).toInt()}%")
            }
        }
    }

}

@Preview
@Composable
fun AddStatPreview() {

    GamifyLivingTheme {

        AddStat(
            name = "",
            value = 0F,
            onNameChange = {},
            onValueChange = {},
            onClose = {},
            onSave = {}
        )

    }

}
