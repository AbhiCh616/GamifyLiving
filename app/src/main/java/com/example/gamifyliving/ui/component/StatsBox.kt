package com.example.gamifyliving.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.R
import com.example.gamifyliving.data.model.Stat

@Composable
fun StatsBox(
    stats: List<Stat>?
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(text = stringResource(id = R.string.stats), style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        stats?.forEach {
            IndividualStat(it)
        }
        Spacer(modifier = Modifier.height(12.dp))
        ViewAllButton()
    }
}

@Composable
fun StatsEditButton() {
    Button(
        onClick = {},
        shape = RoundedCornerShape(50)
    ) {
        Icon(
            Icons.Filled.Edit,
            contentDescription = stringResource(R.string.edit),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.edit))
    }
}

@Composable
fun IndividualStat(statDetails: Stat) {
    Card(
        shape = RoundedCornerShape(30),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(statDetails.name)
            IndividualStatBar(progress = statDetails.value * 100)
        }
    }
}

@Composable
fun IndividualStatBar(progress : Float) {
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
        Text("$progress%")
    }
}

@Composable
fun ViewAllButton() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = { }) {
            Text(text = stringResource(id = R.string.view_all))
        }
    }

}

@Preview
@Composable
fun StatBarPreview() {
    val statDetails = Stat("health", 0.25F)
    IndividualStat(statDetails = statDetails)
}

@Preview
@Composable
fun StatsBoxPreview() {
    val stats = listOf<Stat>(
        Stat("health", 0.25F),
        Stat("relationship", 0.10F)
    )
    StatsBox(stats)
}