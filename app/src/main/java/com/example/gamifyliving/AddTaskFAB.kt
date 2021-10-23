package com.example.gamifyliving

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable

@Composable
fun AddTaskFAB() {
    FloatingActionButton(
        onClick = { print("Add task FAB clicked") }
    ) {
        Icon(Icons.Rounded.Add, null)
    }
}