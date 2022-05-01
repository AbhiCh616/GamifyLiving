package com.example.gamifyliving.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.example.gamifyliving.presentation.add_task.AddTaskHandler
import com.example.gamifyliving.presentation.edit_task.EditTaskHandler
import com.example.gamifyliving.presentation.tasks.TasksScreenHandler
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
            TasksScreenHandler(
                onTaskClick = { task ->
                    navController.navigate("${Screen.EditTask.route}/${task.id}")
                },
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
        composable(
            "${Screen.EditTask.route}/{task_id}",
            arguments = listOf(navArgument("task_id") { type = NavType.IntType })
        ) {
            setBottomBarVisibility(Screen.EditTask.hasBottomNavBar)
            EditTaskHandler(
                onClose = {
                    navController.popBackStack()
                }
            )
        }
    }

}
