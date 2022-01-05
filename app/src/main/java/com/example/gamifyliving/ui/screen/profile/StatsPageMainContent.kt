package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.R
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@ExperimentalMaterialApi
@Composable
fun StatsPageMainContent(
    stats: List<Stat>,
    onIndividualStatClick: (Stat) -> Unit,
    showAddStatDialog: () -> Unit,
) {
    Scaffold(
        floatingActionButton = { AddStatFAB(showAddStatDialog = showAddStatDialog) },
        floatingActionButtonPosition = FabPosition.Center
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
                StatsList(stats, onIndividualStatClick)
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun StatsPageMainContentPreview() {
    val stats = listOf(Stat("Health", 40), Stat("Life", 35))

    GamifyLivingTheme {
        StatsPageMainContent(stats = stats, {}) { }
    }
}