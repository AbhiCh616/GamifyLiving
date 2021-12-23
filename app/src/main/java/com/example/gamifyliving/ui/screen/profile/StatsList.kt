package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@Composable
fun StatsList(
    stats: List<Stat>
) {
    LazyColumn {
        items(stats) {
            IndividualStat(it)
        }
    }
}

@Preview
@Composable
fun StatsListPreview() {
    val stats = listOf(
        Stat("health", 0.25F),
        Stat("relationship", 0.10F)
    )

    GamifyLivingTheme {
        StatsList(stats)
    }
}