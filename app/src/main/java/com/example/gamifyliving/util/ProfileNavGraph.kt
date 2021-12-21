package com.example.gamifyliving.util

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.ui.component.Profile

fun NavGraphBuilder.profileGraph(navController: NavController) {
    navigation(startDestination = Screen.Profile.route, route = Screen.ProfileScreen.route) {
        composable(Screen.Profile.route) { Profile() }
    }
}