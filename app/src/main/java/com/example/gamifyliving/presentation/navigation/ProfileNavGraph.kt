package com.example.gamifyliving.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.gamifyliving.presentation.add_stat.AddStatHandler
import com.example.gamifyliving.presentation.edit_stat.EditStatHandler
import com.example.gamifyliving.presentation.profile.Profile
import com.example.gamifyliving.presentation.stats.StatsScreenHandler
import com.example.gamifyliving.presentation.util.BottomNavigationScreen
import com.example.gamifyliving.presentation.util.Screen

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
fun NavGraphBuilder.profileGraph(
    navController: NavHostController,
    setBottomBarVisibility: (Boolean) -> Unit
) {

    navigation(
        startDestination = Screen.Stats.route,
        route = BottomNavigationScreen.ProfileGroup.route
    ) {
        composable(Screen.Profile.route) {
            setBottomBarVisibility(Screen.Profile.hasBottomNavBar)
            Profile()
        }
        composable(Screen.Stats.route) {
            setBottomBarVisibility(Screen.Stats.hasBottomNavBar)
            StatsScreenHandler(
                onAddButtonClick = {
                    navController.navigate(Screen.AddStat.route)
                },
                onStatClick = { stat ->
                    navController.navigate("${Screen.EditStat.route}/${stat.id}")
                }
            )
        }
        composable(Screen.AddStat.route) {
            setBottomBarVisibility(Screen.AddStat.hasBottomNavBar)
            AddStatHandler(
                onClose = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            "${Screen.EditStat.route}/{stat_id}",
            arguments = listOf(navArgument("stat_id") { type = NavType.IntType })
        ) {
            setBottomBarVisibility(Screen.EditStat.hasBottomNavBar)
            EditStatHandler(
                onClose = {
                    navController.popBackStack()
                }
            )
        }
    }

}
