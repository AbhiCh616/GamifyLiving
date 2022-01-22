package com.example.gamifyliving.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.presentation.add_task.AddTaskHandler
import com.example.gamifyliving.presentation.tasks.TasksScreen
import com.example.gamifyliving.presentation.util.BottomNavigationScreen
import com.example.gamifyliving.presentation.util.Screen

@ExperimentalMaterialApi
fun NavGraphBuilder.taskGraph(
    navController: NavHostController,
    setBottomBarVisibility: (Boolean) -> Unit
) {
    navigation(
        startDestination = Screen.Tasks.route,
        route = BottomNavigationScreen.TaskGroup.route
    ) {
        composable(Screen.Tasks.route) {
            setBottomBarVisibility(Screen.Tasks.hasBottomNavBar)
            TasksScreen(
                onTaskClick = {},
                onAddButtonClick = {
                    navController.navigate(Screen.AddTask.route)
                }
            )
        }
        composable(Screen.AddTask.route) {
            setBottomBarVisibility(Screen.AddTask.hasBottomNavBar)
            AddTaskHandler(
                onClose = {
                    navController.popBackStack()
                }
            )
        }
    }
}
