package com.example.gamifyliving.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.presentation.add_stat.AddStatHandler
import com.example.gamifyliving.presentation.profile.Profile
import com.example.gamifyliving.presentation.stats.StatsScreen
import com.example.gamifyliving.presentation.util.BottomNavigationScreen
import com.example.gamifyliving.presentation.util.Screen

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.profileGraph(
    navController: NavHostController,
    setBottomNavBar: (Boolean) -> Unit
) {
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
            StatsScreen(
                onAddButtonClick = {
                    navController.navigate(Screen.AddStat.route)
                },
                onStatClick = {
                }
            )
        }
        composable(Screen.AddStat.route) {
            setBottomNavBar(Screen.AddStat.hasBottomNavBar)
            AddStatHandler(onClose = { navController.popBackStack() })
        }
    }
}
