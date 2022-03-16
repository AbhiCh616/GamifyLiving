package com.example.gamifyliving.presentation.store

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
    viewModel: StoreViewModel = hiltViewModel()
) {
    val storeItems by viewModel.storeItems.collectAsState(initial = emptyList())

    Store(
        storeItems = storeItems,
        editStoreItem = {},
        onBuyButtonClick = {}
    )
}

@Composable
fun Store(
    storeItems: List<StoreItem>,
    editStoreItem: (StoreItem) -> Unit,
    onBuyButtonClick: (StoreItem) -> Unit
) {
    Scaffold {
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
