package com.example.gamifyliving.presentation.add_store_item

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
fun AddStoreItemHandler(
    onClose: () -> Unit,
    viewModel: AddStoreItemViewModel = hiltViewModel()
) {

    AddStoreItem(
        name = viewModel.name,
        costCoins = viewModel.costCoins,
        onNameChange = viewModel::onNameChange,
        onCostCoinsChange = viewModel::onCostCoinsChange,
        onClose = onClose,
        onSave = {
            viewModel.onSaveClicked()
            onClose()
        }
    )

}

@Composable
fun AddStoreItem(
    name: String,
    costCoins: String,
    onNameChange: (String) -> Unit,
    onCostCoinsChange: (String) -> Unit,
    onClose: () -> Unit,
    onSave: () -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.addStoreItem)) },
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
                    label = { Text(stringResource(id = R.string.storeItemName)) }
                )
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = costCoins,
                    onValueChange = onCostCoinsChange,
                    label = { Text(stringResource(id = R.string.costCoins)) }
                )
            }
        }
    }

}