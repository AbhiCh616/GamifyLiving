package com.example.gamifyliving.presentation.stats

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.presentation.component.StatsList
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme
import com.example.gamifyliving.presentation.util.getStatValueFromProgress

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun StatsScreenHandler(
    onAddButtonClick: () -> Unit,
    onStatClick: (Stat) -> Unit,
    viewModel: StatsViewModel = hiltViewModel()
) {

    val stats by viewModel.stats.collectAsState(initial = emptyList())
    val coins by viewModel.coins.collectAsState(initial = 0)

    StatsScreen(
        stats = stats,
        coins = coins,
        onAddButtonClick = onAddButtonClick,
        onStatClick = onStatClick
    )

}

@ExperimentalMaterialApi
@Composable
fun StatsScreen(
    stats: List<Stat>,
    coins: Int,
    onAddButtonClick: () -> Unit,
    onStatClick: (Stat) -> Unit
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddButtonClick) {
                Icon(Icons.Rounded.Add, null)
            }
        },
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.stats),
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                CoinsChip(coins = coins)
                Spacer(modifier = Modifier.height(8.dp))
                StatsList(stats = stats, onStatClick = onStatClick)
            }
        }
    }

}

@Composable
fun CoinsChip(coins: Int) {
    Row {
        Icon(
            Icons.Rounded.Star,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = coins.toString())
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun StatsScreenPreview() {

    val stats = listOf(
        Stat(name = "health", value = getStatValueFromProgress(45F)),
        Stat(name = "job", value = getStatValueFromProgress(67F))
    )

    GamifyLivingTheme {

        StatsScreen(
            stats = stats,
            coins = 0,
            onAddButtonClick = {},
            onStatClick = {}
        )

    }

}