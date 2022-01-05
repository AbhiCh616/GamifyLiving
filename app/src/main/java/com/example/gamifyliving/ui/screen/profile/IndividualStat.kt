package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.util.getProgressFromStatValue

@ExperimentalMaterialApi
@Composable
fun IndividualStat(
    statDetails: Stat,
    modifier: Modifier = Modifier,
    onClick: (Stat) -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onClick(statDetails)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(statDetails.name)
            IndividualStatBar(getProgressFromStatValue(statDetails.value))
        }
    }
}

@Composable
fun IndividualStatBar(progress: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            progress = progress / 100,
            modifier = Modifier
                .width(64.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text("${progress.toInt()}%")
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun IndividualStatPreview() {
    val statDetails = Stat("health", 0.25F)

    GamifyLivingTheme {
        IndividualStat(statDetails = statDetails) {}
    }
}