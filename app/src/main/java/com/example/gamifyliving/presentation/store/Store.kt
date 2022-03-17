package com.example.gamifyliving.presentation.store

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.StoreItem

@Composable
fun StoreHandler(
    addNewStoreItem: () -> Unit,
    viewModel: StoreViewModel = hiltViewModel()
) {
    val storeItems by viewModel.storeItems.collectAsState(initial = emptyList())

    Store(
        storeItems = storeItems,
        editStoreItem = {},
        onBuyButtonClick = {},
        addNewStoreItem = addNewStoreItem
    )
}

@Composable
fun Store(
    storeItems: List<StoreItem>,
    editStoreItem: (StoreItem) -> Unit,
    onBuyButtonClick: (StoreItem) -> Unit,
    addNewStoreItem: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = addNewStoreItem) {
                Icon(Icons.Rounded.Add, null)
            }
        },
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.store),
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                StoreItemsList(
                    storeItems = storeItems,
                    editStoreItem = editStoreItem,
                    onBuyButtonClick = onBuyButtonClick
                )
            }
        }
    }
}
