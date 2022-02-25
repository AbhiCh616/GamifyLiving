package com.example.gamifyliving.presentation.edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Stat

@Composable
fun EditRewardCardHandler(
    reward: Reward,
    stats: List<Stat>,
    modifier: Modifier = Modifier
) {
    var selectedStat by remember {
        mutableStateOf(
            stats.single { stat -> stat.uid == reward.statId }
        )
    }
    var points by remember { mutableStateOf(reward.points) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    EditRewardCard(
        isDropdownExpanded = isDropdownExpanded,
        expandDropdown = { isDropdownExpanded = true },
        dismissDropdown = { isDropdownExpanded = false },
        selectedStat = selectedStat,
        onStatChange = { selectedStat = it },
        points = points,
        onPointsChange = { points = it },
        stats = stats,
        modifier = modifier
    )
}

@Composable
fun EditRewardCard(
    isDropdownExpanded: Boolean,
    expandDropdown: () -> Unit,
    dismissDropdown: () -> Unit,
    selectedStat: Stat,
    onStatChange: (Stat) -> Unit,
    points: Int,
    onPointsChange: (Int) -> Unit,
    stats: List<Stat>,
    modifier: Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            StatsDropdown(
                isExpanded = isDropdownExpanded,
                expandDropdown = expandDropdown,
                dismissDropdown = dismissDropdown,
                selectedStat = selectedStat,
                onStatChange = onStatChange,
                stats = stats
            )
            Spacer(modifier = Modifier.width(16.dp))
            TextField(
                value = points.toString(),
                onValueChange = { onPointsChange(it.toInt()) }
            )
        }
    }
}

@Composable
fun StatsDropdown(
    isExpanded: Boolean,
    expandDropdown: () -> Unit,
    dismissDropdown: () -> Unit,
    selectedStat: Stat,
    onStatChange: (Stat) -> Unit,
    stats: List<Stat>
) {
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart)
    ) {
        TextButton(onClick = expandDropdown) {
            Text(text = selectedStat.name)
        }
        DropdownMenu(
            expanded = isExpanded,
            onDismissRequest = dismissDropdown
        ) {
            stats.forEach {
                DropdownMenuItem(
                    onClick = { onStatChange(it) }
                ) {
                    Text(text = it.name)
                }
            }
        }
    }
}