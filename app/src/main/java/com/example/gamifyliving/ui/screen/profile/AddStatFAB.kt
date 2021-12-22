package com.example.gamifyliving.ui.screen.profile

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.viewmodel.ProfileViewModel

@Composable
fun AddStatFAB(viewModel: ProfileViewModel) {
    FloatingActionButton(onClick = { viewModel.insertStat(Stat("ABC", 0.5F)) }) {
        Icon(Icons.Rounded.Add, null)
    }
}