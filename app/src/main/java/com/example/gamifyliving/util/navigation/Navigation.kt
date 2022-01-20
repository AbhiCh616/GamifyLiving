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
fun Navigation(navController: NavHostController, setBottomNavBar: (Boolean) -> Unit) {
    NavHost(navController = navController, startDestination = BottomNavigationScreen.Home.route) {
        composable(BottomNavigationScreen.Home.route) {
            setBottomNavBar(BottomNavigationScreen.Home.hasBottomNavBar)
            Home()
        }
        composable(BottomNavigationScreen.Tasks.route) {
            setBottomNavBar(BottomNavigationScreen.Tasks.hasBottomNavBar)
            Tasks(navController)
        }
        composable(BottomNavigationScreen.Rewards.route) {
            setBottomNavBar(BottomNavigationScreen.Rewards.hasBottomNavBar)
            Rewards()
        }
        profileGraph(navController, setBottomNavBar)
    }
}
