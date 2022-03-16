package com.example.gamifyliving.presentation.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamifyliving.presentation.home.Home
import com.example.gamifyliving.presentation.store.Store
import com.example.gamifyliving.presentation.util.BottomNavigationScreen

@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@Composable
fun Navigation(navController: NavHostController, setBottomBarVisibility: (Boolean) -> Unit) {

    NavHost(navController = navController, startDestination = BottomNavigationScreen.Home.route) {
        composable(BottomNavigationScreen.Home.route) {
            setBottomBarVisibility(BottomNavigationScreen.Home.hasBottomNavBar)
            Home()
        }
        composable(BottomNavigationScreen.Store.route) {
            setBottomBarVisibility(BottomNavigationScreen.Store.hasBottomNavBar)
            Store()
        }
        profileGraph(
            setBottomBarVisibility = setBottomBarVisibility,
            navController = navController
        )
        taskGraph(
            setBottomBarVisibility = setBottomBarVisibility,
            navController = navController
        )
    }

}
