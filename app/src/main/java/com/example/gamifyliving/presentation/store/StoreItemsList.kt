package com.example.gamifyliving.presentation.store

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.domain.model.StoreItem

@Composable
fun StoreItemsList(
    storeItems: List<StoreItem>,
    editStoreItem: (StoreItem) -> Unit,
    onBuyButtonClick: (StoreItem) -> Unit
) {
    LazyColumn {
        items(storeItems) { storeItem ->
            StoreItemCard(
                storeItem = storeItem,
                onCardClick = editStoreItem,
                onBuyButtonClick = onBuyButtonClick,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
    }
}
