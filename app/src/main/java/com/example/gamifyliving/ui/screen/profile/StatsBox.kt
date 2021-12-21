package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamifyliving.R
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.util.navigation.Screen

@Composable
fun StatsBox(
    navController: NavController,
    stats: List<Stat>?,
    inEditMode: Boolean
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(text = stringResource(id = R.string.stats), style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        StatsList(stats, inEditMode)
        Spacer(modifier = Modifier.height(12.dp))
        ViewAllButton(navController)
    }
}

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
fun IndividualStat(statDetails: Stat, inEditMode: Boolean) {
    Card(
        shape = RoundedCornerShape(30),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(statDetails.name)
                IndividualStatBar(progress = statDetails.value * 100)
            }
            if (inEditMode) {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Icon(Icons.Rounded.Edit, "edit stat")
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Rounded.Delete, "delete stat", tint = Color.Red)
                    }
                }
            }
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
        Text("$progress%")
    }
}

@Composable
fun ViewAllButton(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = {
            navController.navigate(Screen.Stats.route) {
                launchSingleTop = true
            }
        }) {
            Text(text = stringResource(id = R.string.view_all))
        }
    }

}

@Preview
@Composable
fun StatBarPreview() {
    val statDetails = Stat("health", 0.25F)
    IndividualStat(statDetails = statDetails, true)
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