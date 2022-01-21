package com.example.gamifyliving.util.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.ui.screen.profile.Profile
import com.example.gamifyliving.ui.screen.profile.StatsScreen

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.profileGraph(setBottomNavBar: (Boolean) -> Unit) {
    navigation(
        startDestination = Screen.Stats.route,
        route = BottomNavigationScreen.ProfileGroup.route
    ) {
        composable(Screen.Profile.route) {
            setBottomNavBar(Screen.Profile.hasBottomNavBar)
            Profile()
        }
        composable(Screen.Stats.route) {
            setBottomNavBar(Screen.Stats.hasBottomNavBar)
            StatsScreen()
        }
    }
}
