package com.example.gamifyliving.util.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.ui.screen.add_stat.AddStatHandler
import com.example.gamifyliving.ui.screen.profile.Profile
import com.example.gamifyliving.ui.screen.stats.StatsScreen

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
