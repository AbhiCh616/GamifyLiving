package com.example.gamifyliving.util.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.ui.screen.profile.Profile
import com.example.gamifyliving.ui.screen.profile.StatsPage

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.profileGraph(navController: NavController) {
    navigation(
        startDestination = Screen.Profile.route,
        route = BottomNavigationScreen.ProfileGroup.route
    ) {
        composable(Screen.Profile.route) { Profile(navController) }
        composable(Screen.Stats.route) { StatsPage() }
    }
}
