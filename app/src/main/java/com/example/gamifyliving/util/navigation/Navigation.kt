package com.example.gamifyliving.util.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamifyliving.ui.screen.home.Home
import com.example.gamifyliving.ui.screen.rewards.Rewards
import com.example.gamifyliving.ui.screen.tasks.Tasks

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavigationScreen.Home.route) {
        composable(BottomNavigationScreen.Home.route) { Home(navController) }
        composable(BottomNavigationScreen.Tasks.route) { Tasks(navController) }
        composable(BottomNavigationScreen.Rewards.route) { Rewards(navController) }
        profileGraph(navController)
    }
}
