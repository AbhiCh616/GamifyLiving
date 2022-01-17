package com.example.gamifyliving.ui.screen.tasks

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.data.model.Task
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

@ExperimentalMaterialApi
@Composable
fun IndividualTask(
    task: Task,
    modifier: Modifier = Modifier,
    onCheckboxClicked: (Task) -> Unit,
    onClick: (Task) -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onClick(task)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = task.status, onCheckedChange = { onCheckboxClicked(task) })
            Spacer(modifier = Modifier.width(16.dp))
            Text(task.name)
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun IndividualTaskPreview() {
    val task = Task("Untitled Task", true)
    GamifyLivingTheme {
        IndividualTask(task, onCheckboxClicked = {}, onClick = {})
    }
}