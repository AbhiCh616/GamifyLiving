package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.gamifyliving.GamifyLivingApplication
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.ui.component.BottomNavigationBar
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.util.navigation.Screen
import com.example.gamifyliving.viewmodel.ProfileViewModel
import com.example.gamifyliving.viewmodel.ProfileViewModelFactory

@Composable
fun Profile(
    navController: NavController,
    viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            (LocalContext.current.applicationContext as GamifyLivingApplication).statRepository
        )
    )
) {
    val stats by viewModel.stats.observeAsState(emptyList())

    ProfileContent(stats = stats, navController = navController)
}

@Composable
fun ProfileContent(
    stats: List<Stat>,
    navController: NavController,
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) {
        ProfileMainContent(stats = stats) {
            navController.navigate(Screen.Stats.route) {
                launchSingleTop = true
            }
        }
    }
}

@Composable
fun ProfileMainContent(
    stats: List<Stat>,
    navigateToStatsPage: () -> Unit,
) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            StatsBox(
                stats,
                Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
                navigateToStatsPage
            )
        }
    }
}

@Preview
@Composable
fun ProfileMainContentPreview() {
    val stats = listOf(Stat("Health", 0.5F))

    GamifyLivingTheme {
        ProfileMainContent(stats = stats) { }
    }
}