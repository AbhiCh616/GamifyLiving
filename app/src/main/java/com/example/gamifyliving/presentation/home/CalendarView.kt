package com.example.gamifyliving.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.domain.entity.Task

@Composable
fun CalendarView(tasks: List<Task>, onCheckboxClick: (Task) -> Unit, onTaskClick: (Task) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(tasks, key = { task ->
            task.id
        }) { task ->
            CalendarViewTask(task = task, onCheckboxClick, onClick = onTaskClick)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CalendarViewTask(task: Task, onCheckboxClick: (Task) -> Unit, onClick: (Task) -> Unit) {
    Row(
        Modifier.height(intrinsicSize = IntrinsicSize.Min)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(text = task.schedule!!.timeSpan!!.startTime.toString())
            Text(text = task.schedule!!.timeSpan!!.endTime.toString())
        }
        Spacer(modifier = Modifier.width(12.dp))
        Card(
            onClick = {
                onClick(task)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = task.name,
                    style = if (task.status)
                        TextStyle(textDecoration = TextDecoration.LineThrough) else TextStyle()
                )
                Checkbox(checked = task.status, onCheckedChange = { onCheckboxClick(task) })
            }
        }
    }
}