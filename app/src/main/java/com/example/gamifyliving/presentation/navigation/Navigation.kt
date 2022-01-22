package com.example.gamifyliving.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamifyliving.presentation.add_task.AddTaskHandler
import com.example.gamifyliving.presentation.home.Home
import com.example.gamifyliving.presentation.rewards.Rewards
import com.example.gamifyliving.presentation.tasks.TasksScreen
import com.example.gamifyliving.presentation.util.BottomNavigationScreen
import com.example.gamifyliving.presentation.util.Screen

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
            TasksScreen(
                onTaskClick = {},
                onAddButtonClick = { navController.navigate(Screen.AddTask.route) }
            )
        }
        composable(Screen.AddTask.route) {
            setBottomNavBar(Screen.AddTask.hasBottomNavBar)
            AddTaskHandler(onClose = { navController.popBackStack() })
        }
        composable(BottomNavigationScreen.Rewards.route) {
            setBottomNavBar(BottomNavigationScreen.Rewards.hasBottomNavBar)
            Rewards()
        }
        profileGraph(setBottomNavBar = setBottomNavBar, navController = navController)
    }
}
