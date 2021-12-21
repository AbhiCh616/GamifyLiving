package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.gamifyliving.data.model.Stat

@Composable
fun StatsList(
    stats: List<Stat>?,
    inEditMode: Boolean
) {
    Column {
        stats?.forEach {
            IndividualStat(it, inEditMode)
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
    StatsList(stats, false)
}