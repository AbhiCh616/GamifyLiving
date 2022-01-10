package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.data.model.Task
import com.example.gamifyliving.ui.screen.profile.IndividualStatBar
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.util.getProgressFromStatValue

@Composable
fun IndividualTask(task: Task, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(task.name)
        }
    }
}

@Preview
@Composable
fun IndividualTaskPreview() {
    val task = Task("Untitled Task")
    GamifyLivingTheme {
        IndividualTask(task)
    }
}