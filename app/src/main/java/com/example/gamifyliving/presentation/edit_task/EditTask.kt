package com.example.gamifyliving.presentation.edit_task

import android.text.method.TextKeyListener.clear
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.presentation.component.AppDatePicker
import com.example.gamifyliving.presentation.component.AppTimePicker
import java.time.LocalTime

@Composable
fun EditTaskHandler(
    onClose: () -> Unit,
    viewModel: EditTaskViewModel = hiltViewModel()
) {

    val stats by viewModel.stats.collectAsState(initial = emptyList())

    EditTask(
        onClose = onClose,
        onSave = {
            viewModel.onSaveClicked()
            onClose()
        },
        name = viewModel.name,
        coins = viewModel.coins,
        scheduledDate = viewModel.scheduledDate,
        startTime = viewModel.startTime,
        endTime = viewModel.endTime,
        onNameChange = viewModel::onNameChange,
        onCoinsChange = viewModel::onCoinsChange,
        onStartDateChange = viewModel::onScheduledDateChange,
        onStartTimeChange = viewModel::onStartTimeChange,
        onEndTimeChange = viewModel::onEndTimeChange,
        onDateClear = viewModel::onDateClear,
        onTimeClear = viewModel::onTimeClear,
        onDelete = {
            viewModel.onDeleteClicked()
            onClose()
        },
        rewards = viewModel.rewards,
        stats = stats,
        onAddReward = viewModel::addNewReward,
        editReward = viewModel::editReward,
        deleteReward = viewModel::onDeleteReward,
    )

}

@Composable
fun EditTask(
    onClose: () -> Unit,
    onSave: () -> Unit,
    name: String,
    coins: String,
    scheduledDate: String?,
    startTime: String?,
    endTime: String?,
    onNameChange: (String) -> Unit,
    onCoinsChange: (String) -> Unit,
    onStartDateChange: (Long?) -> Unit,
    onDateClear: () -> Unit,
    onStartTimeChange: (LocalTime?) -> Unit,
    onEndTimeChange: (LocalTime?) -> Unit,
    onTimeClear: () -> Unit,
    onDelete: () -> Unit,
    rewards: List<Reward>,
    stats: List<Stat>,
    onAddReward: () -> Unit,
    editReward: (Reward) -> Unit,
    deleteReward: (Reward) -> Unit,
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.editTask)) },
                navigationIcon = {
                    IconButton(onClick = onClose) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = null)
                    }
                },
                actions = {
                    TextButton(onClick = onSave) {
                        Text(stringResource(id = R.string.save))
                    }
                }
            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                item {
                    Column {
                        TextField(
                            value = name,
                            onValueChange = onNameChange,
                            label = { Text(stringResource(id = R.string.taskName)) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        TextButton(onClick = onDelete) {
                            Text(stringResource(id = R.string.delete))
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        EditCoins(coins = coins, onCoinsChange = onCoinsChange)
                        Spacer(modifier = Modifier.height(32.dp))
                        Text(stringResource(id = R.string.date))
                        Spacer(modifier = Modifier.height(4.dp))
                        Row (
                            verticalAlignment = Alignment.CenterVertically
                                ) {
                            AppDatePicker(
                                dateText = scheduledDate,
                                updateDate = onStartDateChange,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .width(200.dp)
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            TextButton(onClick = onDateClear) {
                                Text(stringResource(id = R.string.clear))
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(stringResource(id = R.string.time))
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AppTimePicker(
                                timeText = startTime,
                                updateTime = onStartTimeChange,
                                displayWhenNotSelected = stringResource(id = R.string.start),
                                        modifier = Modifier.padding(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("-")
                            Spacer(modifier = Modifier.width(8.dp))
                            AppTimePicker(
                                timeText = endTime,
                                updateTime = onEndTimeChange,
                                displayWhenNotSelected = stringResource(id = R.string.end),
                                modifier = Modifier.padding(16.dp)
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            TextButton(onClick = onTimeClear) {
                                Text(stringResource(id = R.string.clear))
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                items(rewards) { reward ->
                    EditRewardCardHandler(
                        reward = reward,
                        stats = stats,
                        editReward = editReward,
                        onDelete = deleteReward,
                        modifier = Modifier.padding(vertical = 8.dp),
                    )
                }
                item {
                    Column {
                        Spacer(modifier = Modifier.height(8.dp))
                        TextButton(onClick = onAddReward) {
                            Text(stringResource(id = R.string.addReward))
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun EditCoins(coins: String, onCoinsChange: (String) -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Rounded.Star, contentDescription = null)
        Spacer(modifier = Modifier.width(8.dp))
        TextField(
            value = coins,
            onValueChange = onCoinsChange,
            modifier = Modifier.width(80.dp)
        )
    }
}
