package com.example.gamifyliving.ui.screen.profile

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.viewmodel.ProfileViewModel

@Composable
fun AddStatFAB(
    showAddStatDialog: () -> Unit
) {
    FloatingActionButton(onClick = { showAddStatDialog() }) {
        Icon(Icons.Rounded.Add, null)
    }
}