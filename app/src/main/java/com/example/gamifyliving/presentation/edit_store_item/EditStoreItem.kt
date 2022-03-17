package com.example.gamifyliving.presentation.edit_store_item

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R

@Composable
fun EditStoreItemHandler(
    onClose: () -> Unit,
    viewModel: EditStoreItemViewModel = hiltViewModel()
) {

    EditStoreItem(
        onClose = onClose,
        onSave = {
            viewModel.onSaveClicked()
            onClose()
        },
        name = viewModel.name,
        onNameChange = viewModel::onNameChange,
        costCoins = viewModel.costCoins,
        onCostCoinsChange = viewModel::onCostCoinsChange,
        onDelete = {
            viewModel.onDeleteClicked()
            onClose()
        }
    )

}

@Composable
fun EditStoreItem(
    onClose: () -> Unit,
    onSave: () -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    costCoins: String,
    onCostCoinsChange: (String) -> Unit,
    onDelete: () -> Unit
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.editStoreItem)) },
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
                    label = { Text(stringResource(id = R.string.storeItemName)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = costCoins,
                    onValueChange = onCostCoinsChange,
                    label = { Text(stringResource(id = R.string.costCoins)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = onDelete) {
                    Text(stringResource(id = R.string.delete))
                }
            }
        }
    }

}
