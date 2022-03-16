package com.example.gamifyliving.presentation.store

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.StoreItem
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StoreItemCard(
    storeItem: StoreItem,
    onCardClick: (StoreItem) -> Unit,
    onBuyButtonClick: (StoreItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onCardClick(storeItem) }
    ) {
        Column(
            horizontalAlignment =
            Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Text(text = storeItem.name)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { onBuyButtonClick(storeItem) }
            ) {
                Text(text = stringResource(id = R.string.buy))
            }
        }
    }
}

@Preview
@Composable
fun StoreItemCardPreview() {
    GamifyLivingTheme {
        StoreItemCard(
            storeItem = StoreItem("Watch Netflix for 30 minutes", 0),
            onCardClick = {},
            onBuyButtonClick = {}
        )
    }
}
