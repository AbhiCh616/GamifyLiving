package com.example.gamifyliving.util.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.ui.screen.profile.Profile
import com.example.gamifyliving.ui.screen.profile.StatsPage
import com.example.gamifyliving.util.navigation.Screen

fun NavGraphBuilder.profileGraph(navController: NavController) {
    navigation(startDestination = Screen.Profile.route, route = Screen.ProfileScreen.route) {
        composable(Screen.Profile.route) { Profile(navController) }
        composable(Screen.Stats.route) { StatsPage() }
    }
}