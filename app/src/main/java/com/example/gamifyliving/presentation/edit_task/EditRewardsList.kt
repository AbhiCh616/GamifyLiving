package com.example.gamifyliving.presentation.edit_task

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.domain.model.Reward
import com.example.gamifyliving.domain.model.Stat

@Composable
fun EditRewardsList(
    rewards: List<Reward>,
    stats: List<Stat>,
    editReward: (Reward) -> Unit,
    deleteReward: (Reward) -> Unit,
) {
    LazyColumn {
        items(rewards) { reward ->
            EditRewardCardHandler(
                reward = reward,
                stats = stats,
                editReward = editReward,
                onDelete = deleteReward,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }
    }
}