package com.example.gamifyliving.ui.screen.profile

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.viewmodel.ProfileViewModel
import com.example.gamifyliving.viewmodel.ProfileViewModelFactory
import kotlin.math.truncate

@ExperimentalComposeUiApi
@Composable
fun StatsPage(
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).statRepository
        )
    )
) {
    val stats by viewModel.stats.observeAsState(emptyList())
    var isAddStatDialogVisible by remember { mutableStateOf(false) }

    StatsPageContent(
        stats = stats,
        isAddStatDialogVisible = isAddStatDialogVisible,
        showAddStatDialog = { isAddStatDialogVisible = true },
        hideAddStatDialog = { isAddStatDialogVisible = false },
    ) { statName, statValue ->
        if (statName != "") {
            viewModel.insertStat(Stat(statName, truncate(statValue * 100) / 100))
        }
    }

}

@ExperimentalComposeUiApi
@Composable
fun StatsPageContent(
    stats: List<Stat>,
    isAddStatDialogVisible: Boolean,
    showAddStatDialog: () -> Unit,
    hideAddStatDialog: () -> Unit,
    createNewStat: (String, Float) -> Unit
) {
    if (isAddStatDialogVisible) {
        AddStatDialog(onClose = hideAddStatDialog, onSave = createNewStat)
    } else {
        StatsPageMainContent(stats = stats, showAddStatDialog = showAddStatDialog)
    }
}