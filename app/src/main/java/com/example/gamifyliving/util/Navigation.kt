package com.example.gamifyliving.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamifyliving.ui.component.Home
import com.example.gamifyliving.ui.component.Profile
import com.example.gamifyliving.ui.component.Rewards
import com.example.gamifyliving.ui.component.Tasks
import com.example.gamifyliving.util.Screen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.ProfileScreen.route) {
        composable(Screen.Home.route) { Home() }
        composable(Screen.Tasks.route) { Tasks() }
        composable(Screen.Rewards.route) { Rewards() }
        profileGraph(navController)
    }
}