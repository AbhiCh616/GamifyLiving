package com.example.gamifyliving.presentation.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.presentation.component.StatsList
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme
import com.example.gamifyliving.presentation.util.getStatValueFromProgress

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun StatsScreenHandler(
    viewModel: StatsViewModel = viewModel(
        factory = StatsViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).statRepository
        )
    ),
    onAddButtonClick: () -> Unit,
    onStatClick: (Stat) -> Unit
) {

    val stats by viewModel.stats.collectAsState(initial = emptyList())

    StatsScreen(
        stats = stats,
        onAddButtonClick = onAddButtonClick,
        onStatClick = onStatClick
    )

}

@ExperimentalMaterialApi
@Composable
fun StatsScreen(
    stats: List<Stat>,
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
                StatsList(stats = stats, onStatClick = onStatClick)
            }
        }
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
            onAddButtonClick = {},
            onStatClick = {}
        )

    }

}