package com.example.gamifyliving

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.viewmodel.ProfileViewModel
import com.example.gamifyliving.viewmodel.ProfileViewModelFactory

@Composable
fun Profile(
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).statRepository
        )
    )
) {
    val stats by viewModel.stats.observeAsState()

    Surface(color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            StatsBox(stats)
        }
    }
}

@Composable
fun StatsBox(
    stats: List<Stat>?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = stringResource(id = R.string.stats), style = MaterialTheme.typography.h6)
                StatsEditButton()
            }
            Spacer(modifier = Modifier.height(16.dp))
            stats?.forEach {
                StatBar(it)
            }
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
fun StatBar(statDetails: Stat) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(statDetails.name)
            Text("${statDetails.value}%")
        }
        Spacer(modifier = Modifier.height(8.dp))
        LinearProgressIndicator(
            progress = statDetails.value / 100,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun StatBarPreview() {
    val statDetails = Stat("health", 20.5F)
    StatBar(statDetails = statDetails)
}

@Preview
@Composable
fun StatsBoxPreview() {
    val stats = listOf<Stat>(Stat("health", 20.5F))
    StatsBox(stats)
}