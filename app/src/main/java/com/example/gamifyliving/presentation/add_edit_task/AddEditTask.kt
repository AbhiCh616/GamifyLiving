package com.example.gamifyliving.presentation.add_edit_task

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.entity.Reward
import com.example.gamifyliving.domain.entity.Stat
import com.example.gamifyliving.presentation.component.AppDatePicker
import com.example.gamifyliving.presentation.component.AppTimePicker
import com.example.gamifyliving.presentation.util.DaysOfWeek
import com.example.gamifyliving.presentation.util.ScheduleType
import com.example.gamifyliving.presentation.util.TaskType
import com.example.gamifyliving.presentation.util.WeekDay
import java.time.LocalDate
import java.time.LocalTime

@Composable
fun AddEditTaskHandler(
    onClose: () -> Unit,
    viewModelAdd: AddEditTaskViewModel = hiltViewModel()
) {

    val stats by viewModelAdd.stats.collectAsState(initial = emptyList())

    AddEditTask(
        onClose = onClose,
        onSave = {
            viewModelAdd.onSaveClicked()
            onClose()
        },
        isNewTask = viewModelAdd.isNewTask,
        taskType = viewModelAdd.taskType,
        onTaskTypeChange = viewModelAdd::onTaskTypeChange,
        name = viewModelAdd.name,
        onNameChange = viewModelAdd::onNameChange,
        onDelete = {
            viewModelAdd.onDeleteClicked()
            onClose()
        },
        coins = viewModelAdd.coins,
        onCoinsChange = viewModelAdd::onCoinsChange,
        scheduledDate = viewModelAdd.scheduledDateText,
        onDateChange = viewModelAdd::onScheduledDateChange,
        onDateClear = viewModelAdd::onDateClear,
        scheduleType = viewModelAdd.scheduleType,
        onScheduleTypeChange = viewModelAdd::onScheduleTypeChange,
        isScheduleDropdownExpanded = viewModelAdd.isScheduleDropdownExpanded,
        scheduleDropdownExpandedChange = viewModelAdd::scheduleDropdownExpandedChange,
        dismissScheduleDropdown = viewModelAdd::dismissScheduleDropdown,
        repeatInterval = viewModelAdd.repeatInterval,
        onRepeatIntervalChange = viewModelAdd::onRepeatIntervalChange,
        daysOfWeek = viewModelAdd.daysOfWeek,
        onDaysOfWeekChange = viewModelAdd::onDaysOfWeekSelectionChange,
        startTime = viewModelAdd.startTimeText,
        onStartTimeChange = viewModelAdd::onStartTimeChange,
        endTime = viewModelAdd.endTimeText,
        onEndTimeChange = viewModelAdd::onEndTimeChange,
        onTimeClear = viewModelAdd::onTimeClear,
        rewards = viewModelAdd.rewards,
        stats = stats,
        onAddReward = viewModelAdd::addNewReward,
        editReward = viewModelAdd::editReward,
        deleteReward = viewModelAdd::onDeleteReward
    )

}

@Composable
fun AddEditTask(
    onClose: () -> Unit,
    onSave: () -> Unit,
    isNewTask: Boolean,
    taskType: TaskType,
    onTaskTypeChange: (TaskType) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    onDelete: () -> Unit,
    coins: String,
    onCoinsChange: (String) -> Unit,
    scheduledDate: String?,
    onDateChange: (LocalDate?) -> Unit,
    onDateClear: () -> Unit,
    scheduleType: ScheduleType?,
    onScheduleTypeChange: (ScheduleType?) -> Unit,
    isScheduleDropdownExpanded: Boolean,
    scheduleDropdownExpandedChange: (Boolean) -> Unit,
    dismissScheduleDropdown: () -> Unit,
    repeatInterval: String?,
    onRepeatIntervalChange: (String) -> Unit,
    daysOfWeek: DaysOfWeek,
    onDaysOfWeekChange: (WeekDay) -> Unit,
    startTime: String?,
    onStartTimeChange: (LocalTime?) -> Unit,
    endTime: String?,
    onEndTimeChange: (LocalTime?) -> Unit,
    onTimeClear: () -> Unit,
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
                        if (isNewTask) {
                            TaskTypeSelector(
                                taskType = taskType,
                                onTaskTypeChange = onTaskTypeChange
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        TextField(
                            value = name,
                            onValueChange = onNameChange,
                            label = { Text(stringResource(id = R.string.taskName)) }
                        )
                        if (!isNewTask) {
                            Spacer(modifier = Modifier.height(16.dp))
                            TextButton(onClick = onDelete) {
                                Text(stringResource(id = R.string.delete))
                            }
                        }
                        if (taskType == TaskType.TODO) {
                            Spacer(modifier = Modifier.height(16.dp))
                            EditCoins(coins = coins, onCoinsChange = onCoinsChange)
                            Spacer(modifier = Modifier.height(32.dp))
                            Text(stringResource(id = R.string.date))
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AppDatePicker(
                                    dateText = scheduledDate,
                                    updateDate = onDateChange,
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .width(200.dp)
                                )
                                Spacer(modifier = Modifier.width(24.dp))
                                TextButton(onClick = onDateClear) {
                                    Text(stringResource(id = R.string.clear))
                                }
                            }
                        }
                        if (taskType == TaskType.HABIT) {
                            Spacer(modifier = Modifier.height(16.dp))
                            ScheduleTypeSelector(
                                expanded = isScheduleDropdownExpanded,
                                onExpandedChange = scheduleDropdownExpandedChange,
                                onDismiss = dismissScheduleDropdown,
                                scheduleType = scheduleType,
                                onScheduleTypeChange = onScheduleTypeChange
                            )
                        }
                        if (taskType == TaskType.HABIT) {
                            if (scheduleType == ScheduleType.REPEAT_AFTER) {
                                Spacer(modifier = Modifier.height(16.dp))
                                TextField(
                                    value = repeatInterval!!,
                                    onValueChange = onRepeatIntervalChange,
                                    label = { Text(stringResource(id = R.string.interval)) },
                                    modifier = Modifier.width(140.dp),
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                )
                            } else if (scheduleType == ScheduleType.DAY_OF_WEEK) {
                                Spacer(modifier = Modifier.height(16.dp))
                                DaysOfWeekSelector(
                                    daysOfWeek = daysOfWeek,
                                    onDaysOfWeekChange = onDaysOfWeekChange
                                )
                            }
                        }
                        if (
                            (taskType == TaskType.TODO && scheduledDate != null) ||
                            (taskType == TaskType.HABIT && scheduleType != null)
                        ) {
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
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
                items(rewards) { reward ->
                    AddEditRewardCardHandler(
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
fun TaskTypeSelector(taskType: TaskType, onTaskTypeChange: (TaskType) -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = taskType == TaskType.TODO,
                onClick = { onTaskTypeChange(TaskType.TODO) }
            )
            Text(text = stringResource(id = R.string.todo))
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = taskType == TaskType.HABIT,
                onClick = { onTaskTypeChange(TaskType.HABIT) }
            )
            Text(text = stringResource(id = R.string.habit))
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
            modifier = Modifier.width(80.dp),
            label = { Text(stringResource(id = R.string.coins)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScheduleTypeSelector(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    scheduleType: ScheduleType?,
    onScheduleTypeChange: (ScheduleType?) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = Modifier.width(240.dp)
    ) {
        TextField(
            readOnly = true,
            value = stringResource(
                id =
                when (scheduleType) {
                    ScheduleType.EVERYDAY -> R.string.everyday
                    ScheduleType.REPEAT_AFTER -> R.string.repeat_after
                    ScheduleType.DAY_OF_WEEK -> R.string.day_of_week
                    null -> R.string.select_schedule
                }
            ),
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
            DropdownMenuItem(
                onClick = {
                    onScheduleTypeChange(null)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.no_selection))
            }
            DropdownMenuItem(
                onClick = {
                    onScheduleTypeChange(ScheduleType.EVERYDAY)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.everyday))
            }
            DropdownMenuItem(
                onClick = {
                    onScheduleTypeChange(ScheduleType.REPEAT_AFTER)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.repeat_after))
            }
            DropdownMenuItem(
                onClick = {
                    onScheduleTypeChange(ScheduleType.DAY_OF_WEEK)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.day_of_week))
            }
        }
    }
}

@Composable
fun DaysOfWeekSelector(
    daysOfWeek: DaysOfWeek,
    onDaysOfWeekChange: (WeekDay) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RadioButton(
                selected = daysOfWeek.sunday,
                onClick = { onDaysOfWeekChange(WeekDay.SUNDAY) }
            )
            Text(text = "S")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RadioButton(
                selected = daysOfWeek.monday,
                onClick = { onDaysOfWeekChange(WeekDay.MONDAY) }
            )
            Text(text = "M")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RadioButton(
                selected = daysOfWeek.tuesday,
                onClick = { onDaysOfWeekChange(WeekDay.TUESDAY) }
            )
            Text(text = "T")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RadioButton(
                selected = daysOfWeek.wednesday,
                onClick = { onDaysOfWeekChange(WeekDay.WEDNESDAY) }
            )
            Text(text = "W")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RadioButton(
                selected = daysOfWeek.thursday,
                onClick = { onDaysOfWeekChange(WeekDay.THURSDAY) }
            )
            Text(text = "T")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RadioButton(
                selected = daysOfWeek.friday,
                onClick = { onDaysOfWeekChange(WeekDay.FRIDAY) }
            )
            Text(text = "F")
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RadioButton(
                selected = daysOfWeek.saturday,
                onClick = { onDaysOfWeekChange(WeekDay.SATURDAY) }
            )
            Text(text = "S")
        }
    }
}
