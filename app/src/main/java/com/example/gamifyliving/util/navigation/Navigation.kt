package com.example.gamifyliving.util.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamifyliving.ui.screen.home.Home
import com.example.gamifyliving.ui.screen.rewards.Rewards
import com.example.gamifyliving.ui.screen.tasks.Tasks

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.ProfileScreen.route) {
        composable(Screen.Home.route) { Home() }
        composable(Screen.Tasks.route) { Tasks() }
        composable(Screen.Rewards.route) { Rewards() }
        profileGraph(navController)
    }
}