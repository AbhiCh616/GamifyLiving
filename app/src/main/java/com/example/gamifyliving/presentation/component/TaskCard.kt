package com.example.gamifyliving.presentation.component

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
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme

@ExperimentalMaterialApi
@Composable
fun TaskCard(
    task: Task,
    modifier: Modifier = Modifier,
    onCheckboxClick: (Task) -> Unit,
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
            Checkbox(checked = task.status, onCheckedChange = { onCheckboxClick(task) })
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = task.name)
        }
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun IndividualTaskPreview() {
    val task = Task(name = "Untitled Task", status = true)
    GamifyLivingTheme {
        TaskCard(task, onCheckboxClick = {}, onClick = {})
    }
}