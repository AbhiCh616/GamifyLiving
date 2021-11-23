package com.example.gamifyliving

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Profile.route) {
        composable(Screen.Home.route) { Home() }
        composable(Screen.Tasks.route) { Tasks() }
        composable(Screen.Rewards.route) { Rewards() }
        composable(Screen.Profile.route) { Rewards() }
    }
}