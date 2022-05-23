package com.example.gamifyliving.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Task
import com.example.gamifyliving.presentation.tasks.TasksList

@Composable
fun HomeHandler(
    onTaskClick: (Task) -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val listTasks by viewModel.listTasks.collectAsState(initial = emptyList())
    val calendarTasks by viewModel.calendarTasks.collectAsState(initial = emptyList())

    Home(
        isViewDropDownExpanded = viewModel.isViewDropDownExpanded,
        onViewDropDownExpandedChange = viewModel::onViewDropDownExpandedChange,
        onViewDropDownDismiss = viewModel::onViewDropDownDismiss,
        view = viewModel.view,
        onViewChange = viewModel::onViewChange,
        listTasks = listTasks,
        calendarTasks = calendarTasks,
        changeTaskStatus = viewModel::onTaskStatusChange,
        onTaskClick = onTaskClick
    )
}

@Composable
fun Home(
    isViewDropDownExpanded: Boolean,
    onViewDropDownExpandedChange: (Boolean) -> Unit,
    onViewDropDownDismiss: () -> Unit,
    view: HomeViewType,
    onViewChange: (HomeViewType) -> Unit,
    listTasks: List<Task>,
    calendarTasks: List<Task>,
    changeTaskStatus: (Task) -> Unit,
    onTaskClick: (Task) -> Unit,
) {
    Scaffold {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                ViewDropDown(
                    expanded = isViewDropDownExpanded,
                    onExpandedChange = onViewDropDownExpandedChange,
                    onDismiss = onViewDropDownDismiss,
                    view = view,
                    onViewChange = onViewChange
                )
                Spacer(modifier = Modifier.height(32.dp))
                if (view == HomeViewType.LIST)
                    TasksList(
                        tasks = listTasks,
                        onCheckboxClick = changeTaskStatus,
                        onTaskClick = onTaskClick
                    )
                if (view == HomeViewType.CALENDAR)
                    CalendarView(
                        tasks = calendarTasks,
                        onCheckboxClick = changeTaskStatus,
                        onTaskClick = onTaskClick
                    )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ViewDropDown(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onDismiss: () -> Unit,
    view: HomeViewType,
    onViewChange: (HomeViewType) -> Unit
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = Modifier.width(180.dp)
    ) {
        TextField(
            readOnly = true,
            value = stringResource(
                id = when (view) {
                    HomeViewType.LIST -> R.string.list_view
                    HomeViewType.CALENDAR -> R.string.calendar_view
                }
            ),
            onValueChange = {},
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
                    onViewChange(HomeViewType.LIST)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.list_view))
            }
            DropdownMenuItem(
                onClick = {
                    onViewChange(HomeViewType.CALENDAR)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(id = R.string.calendar_view))
            }
        }
    }
}
