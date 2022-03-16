package com.example.gamifyliving.presentation.store

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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
                onBuyButtonClick = onBuyButtonClick
            )
        }
    }
}
