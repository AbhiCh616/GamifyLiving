package com.example.gamifyliving

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Profile() {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            StatsBox()
        }
    }
}

@Composable
fun StatsBox() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.stats), style = MaterialTheme.typography.h6)
        }
    }
}

@Composable
fun Stat(statDesc: String, progress: Float) {
    Column(
        modifier = Modifier.width(260.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(statDesc)
            Text("$progress%")
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = progress
        )
    }
}

@Preview
@Composable
fun StatPreview() {
    Stat(statDesc = "Health", progress = 50.5F)
}

@Preview
@Composable
fun StatsBoxPreview() {
    StatsBox()
}