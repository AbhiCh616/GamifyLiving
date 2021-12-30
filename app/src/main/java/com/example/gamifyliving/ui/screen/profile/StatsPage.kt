package com.example.gamifyliving.ui.screen.profile

import androidx.compose.material.ExperimentalMaterialApi
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

@ExperimentalMaterialApi
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
    var isEditStatDialogVisible by remember { mutableStateOf(false) }
    var selectedStat: Stat? by remember { mutableStateOf(null) }

    StatsPageContent(
        stats = stats,
        isAddStatDialogVisible = isAddStatDialogVisible,
        showAddStatDialog = { isAddStatDialogVisible = true },
        hideAddStatDialog = { isAddStatDialogVisible = false },
        createNewStat = { statName, statValue ->
            if (statName != "") {
                viewModel.insertStat(Stat(statName, truncate(statValue * 100) / 100))
            }
        },
        isEditStatDialogVisible = isEditStatDialogVisible,
        showEditStatDialog = { isEditStatDialogVisible = true },
        hideEditStatDialog = { isEditStatDialogVisible = false },
        editStat = { stat, statName, statValue ->
            if (statName != "") {
                viewModel.updateStatValues(stat, statName, statValue)
            }
        },
        onIndividualStatClick = {
            selectedStat = it
            isEditStatDialogVisible = true
        },
        selectedStat = selectedStat,
        onStatDelete = {
            viewModel.deleteStat(it)
        }
    )

}

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun StatsPageContent(
    stats: List<Stat>,
    isAddStatDialogVisible: Boolean,
    showAddStatDialog: () -> Unit,
    hideAddStatDialog: () -> Unit,
    createNewStat: (String, Float) -> Unit,
    isEditStatDialogVisible: Boolean,
    showEditStatDialog: () -> Unit,
    hideEditStatDialog: () -> Unit,
    editStat: (Stat, String, Float) -> Unit,
    onIndividualStatClick: (Stat) -> Unit,
    selectedStat: Stat?,
    onStatDelete: (Stat) -> Unit
) {
    if (isAddStatDialogVisible) {
        AddStatDialog(onClose = hideAddStatDialog, onSave = createNewStat)
    }
    else if(isEditStatDialogVisible) {
        if (selectedStat != null) {
            EditStatDialog(stat = selectedStat, onClose = hideEditStatDialog, onSave = editStat, onStatDelete = onStatDelete)
        }
    }
    else {
        StatsPageMainContent(stats = stats, showAddStatDialog = showAddStatDialog, onIndividualStatClick = onIndividualStatClick)
    }
}