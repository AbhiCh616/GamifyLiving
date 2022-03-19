package com.example.gamifyliving.presentation.edit_task

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme

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
        onNameChange = viewModel::onNameChange,
        onCoinsChange = viewModel::onCoinsChange,
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
    onNameChange: (String) -> Unit,
    onCoinsChange: (String) -> Unit,
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                Spacer(modifier = Modifier.height(16.dp))
                EditRewardsList(
                    rewards = rewards,
                    stats = stats,
                    editReward = editReward,
                    deleteReward = deleteReward
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextButton(onClick = onAddReward) {
                    Text(stringResource(id = R.string.addReward))
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

@Preview
@Composable
fun EditTaskPreview() {

    GamifyLivingTheme {

        EditTask(
            name = "",
            onNameChange = {},
            onDelete = {},
            onAddReward = {},
            onClose = {},
            onSave = {},
            rewards = emptyList(),
            stats = emptyList(),
            editReward = {},
            deleteReward = {},
            coins = "",
            onCoinsChange = {}
        )

    }

}
