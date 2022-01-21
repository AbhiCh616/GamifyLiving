package com.example.gamifyliving.util.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gamifyliving.R

sealed class Screen(
    val route: String,
    val hasBottomNavBar: Boolean = false
) {

    object Stats : Screen(route = "stats", hasBottomNavBar = true)
    object Profile : Screen(route = "profile", hasBottomNavBar = true)

}

sealed class BottomNavigationScreen(
    route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    hasBottomNavBar: Boolean = false,
) : Screen(route, hasBottomNavBar) {

    object Home :
        BottomNavigationScreen(
            route = "home",
            resourceId = R.string.home,
            icon = Icons.Rounded.Home,
            hasBottomNavBar = true
        )

    object Tasks :
        BottomNavigationScreen(
            route = "tasks",
            resourceId = R.string.tasks,
            icon = Icons.Rounded.CheckCircle,
            hasBottomNavBar = true
        )

    object Rewards :
        BottomNavigationScreen(
            route = "rewards",
            resourceId = R.string.rewards,
            icon = Icons.Rounded.Star,
            hasBottomNavBar = true
        )

    object ProfileGroup :
        BottomNavigationScreen(
            route = "profile_group",
            resourceId = R.string.profile,
            icon = Icons.Rounded.Person
        )

}
