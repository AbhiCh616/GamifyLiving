package com.example.gamifyliving.ui.screen.stats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.R
import com.example.gamifyliving.domain.model.Stat
import com.example.gamifyliving.ui.screen.component.StatsList
import com.example.gamifyliving.ui.screen.profile.AddStatDialog
import com.example.gamifyliving.ui.screen.profile.EditStatDialog
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.util.getStatValueFromProgress
import com.example.gamifyliving.viewmodel.ProfileViewModel
import com.example.gamifyliving.viewmodel.ProfileViewModelFactory

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun StatsScreen(
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).statRepository
        )
    )
) {
    val stats by viewModel.getAllStats().observeAsState(emptyList())
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
                viewModel.addStat(Stat(statName, getStatValueFromProgress(statValue)))
            }
        },
        isEditStatDialogVisible = isEditStatDialogVisible,
        hideEditStatDialog = { isEditStatDialogVisible = false },
        editStat = { stat, statName, statValue ->
            if (statName != "") {
                viewModel.updateStatValues(stat, statName, getStatValueFromProgress(statValue))
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
    hideEditStatDialog: () -> Unit,
    editStat: (Stat, String, Float) -> Unit,
    onIndividualStatClick: (Stat) -> Unit,
    selectedStat: Stat?,
    onStatDelete: (Stat) -> Unit
) {
    if (isAddStatDialogVisible) {
        AddStatDialog(onClose = hideAddStatDialog, onSave = createNewStat)
    } else if (isEditStatDialogVisible && selectedStat != null) {
        EditStatDialog(
            stat = selectedStat,
            onClose = hideEditStatDialog,
            onSave = editStat,
            onStatDelete = onStatDelete
        )
    } else {
        StatsPageMainContent(
            stats = stats,
            showAddStatDialog = showAddStatDialog,
            onIndividualStatClick = onIndividualStatClick
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun StatsPageMainContent(
    stats: List<Stat>,
    onIndividualStatClick: (Stat) -> Unit,
    showAddStatDialog: () -> Unit,
) {
    Scaffold(
        floatingActionButton = { AddStatFAB(showAddStatDialog = showAddStatDialog) },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.stats),
                    style = MaterialTheme.typography.h5
                )
                Spacer(modifier = Modifier.height(16.dp))
                StatsList(stats, onIndividualStatClick)
            }
        }
    }
}

@Composable
fun AddStatFAB(
    showAddStatDialog: () -> Unit
) {
    FloatingActionButton(onClick = showAddStatDialog) {
        Icon(Icons.Rounded.Add, null)
    }
}

@ExperimentalMaterialApi
@Preview
@Composable
fun StatsPageMainContentPreview() {
    val stats = listOf(Stat("Health", 40), Stat("Life", 35))

    GamifyLivingTheme {
        StatsPageMainContent(stats = stats, {}) { }
    }
}

@Preview
@Composable
fun AddStatFABPreview() {
    GamifyLivingTheme {
        AddStatFAB {}
    }
}