package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@ExperimentalMaterialApi
@Composable
fun StatsList(
    stats: List<Stat>,
    onIndividualStatClick: (Stat) -> Unit,
) {
    LazyColumn {
        items(stats) {
            IndividualStat(
                it,
                Modifier.padding(vertical = 8.dp),
                onIndividualStatClick
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun StatsListPreview() {
    val stats = listOf(
        Stat("health", 0.25F),
        Stat("relationship", 0.10F)
    )

    GamifyLivingTheme {
        StatsList(stats) {}
    }
}