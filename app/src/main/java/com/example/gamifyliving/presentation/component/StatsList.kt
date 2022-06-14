package com.example.gamifyliving.presentation.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.domain.entity.Stat
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme
import com.example.gamifyliving.presentation.util.getStatValueFromSliderValue

@ExperimentalMaterialApi
@Composable
fun StatsList(
    stats: List<Stat>,
    onStatClick: (Stat) -> Unit,
) {

    LazyColumn {
        items(stats) { stat ->
            StatCard(
                stat = stat,
                modifier = Modifier.padding(vertical = 8.dp),
                onClick = onStatClick
            )
        }
    }

}

@ExperimentalMaterialApi
@Preview
@Composable
fun StatsListPreview() {

    val stats = listOf(
        Stat(name = "health", getStatValueFromSliderValue(progress = 0.25F)),
        Stat(name = "relationship", getStatValueFromSliderValue(progress = 0.50F))
    )

    GamifyLivingTheme {

        StatsList(
            stats = stats,
            onStatClick = {}
        )

    }

}
