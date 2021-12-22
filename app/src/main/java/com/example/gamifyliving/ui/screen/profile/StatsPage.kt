package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.R
import com.example.gamifyliving.viewmodel.ProfileViewModel
import com.example.gamifyliving.viewmodel.ProfileViewModelFactory

@Composable
fun StatsPage(
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).statRepository
        )
    )
) {
    val stats by viewModel.stats.observeAsState()

    Surface(color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {
            Text(text = stringResource(id = R.string.stats), style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(16.dp))
            StatsList(stats)
        }
    }
}