package com.example.gamifyliving.presentation.component

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
import com.example.gamifyliving.domain.model.entity.Stat
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme
import com.example.gamifyliving.presentation.util.getProgressFromStatValue
import com.example.gamifyliving.presentation.util.getStatValueFromSliderValue

@ExperimentalMaterialApi
@Composable
fun StatCard(
    stat: Stat,
    modifier: Modifier = Modifier,
    onClick: (Stat) -> Unit,
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onClick(stat)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = stat.name)
            StatProgressBar(progress = getProgressFromStatValue(stat.value))
        }
    }

}

@Composable
fun StatProgressBar(progress: Float) {

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .width(64.dp)
                .height(12.dp)
                .clip(RoundedCornerShape(50))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = "${(progress * 100).toInt()}%")
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun StatCardPreview() {

    val statDetails = Stat(
        name = "health",
        getStatValueFromSliderValue(progress = 0.25F)
    )

    GamifyLivingTheme {
        StatCard(
            stat = statDetails,
            onClick = {}
        )
    }

}
