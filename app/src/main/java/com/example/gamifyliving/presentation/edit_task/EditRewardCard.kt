package com.example.gamifyliving.presentation.edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.R
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
        dropDownExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
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
    dropDownExpandedChange: (Boolean) -> Unit,
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
                expanded = isDropdownExpanded,
                onExpandedChange = dropDownExpandedChange,
                onDismiss = dismissDropdown,
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun StatsDropdown(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    selectedStat: Stat,
    onStatChange: (Stat) -> Unit,
    stats: List<Stat>
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = Modifier.width(160.dp)
    ) {
        TextField(
            readOnly = true,
            value = selectedStat.name,
            onValueChange = {},
            label = { Text(text = stringResource(id = R.string.stat)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded
                )
            }
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = onDismiss
        ) {
            stats.forEach {
                DropdownMenuItem(
                    onClick = {
                        onStatChange(it)
                        onDismiss()
                    }
                ) {
                    Text(text = it.name)
                }
            }
        }
    }
}
