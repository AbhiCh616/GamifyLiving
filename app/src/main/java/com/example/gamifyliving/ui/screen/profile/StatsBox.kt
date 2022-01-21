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
import com.example.gamifyliving.util.getStatValueFromProgress

@ExperimentalMaterialApi
@Composable
fun StatsBox(
    stats: List<Stat>,
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(id = R.string.stats), style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        stats.take(3).forEach {
            StatCard(it, Modifier.padding(vertical = 8.dp)) {}
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextButton(onClick = onViewAllClick) {
            Text(text = stringResource(id = R.string.view_all))
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun StatsBoxPreview() {
    val stats = listOf(
        Stat("Health", getStatValueFromProgress(0.25F)),
        Stat("Relationship", getStatValueFromProgress(0.75F))
    )

    GamifyLivingTheme {
        Surface {
            StatsBox(
                stats = stats,
            ) { }
        }
    }
}