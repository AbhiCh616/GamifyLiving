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
    val route: String
) {

    object Stats : Screen(route = "stats")
    object Profile : Screen(route = "profile")

}

sealed class BottomNavigationScreen(
    route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
) : Screen(route) {

    object Home :
        BottomNavigationScreen(
            route = "home",
            resourceId = R.string.home,
            icon = Icons.Rounded.Home
        )

    object Tasks :
        BottomNavigationScreen(
            route = "tasks",
            resourceId = R.string.tasks,
            icon = Icons.Rounded.CheckCircle
        )

    object Rewards :
        BottomNavigationScreen(
            route = "rewards",
            resourceId = R.string.rewards,
            icon = Icons.Rounded.Star
        )

    object ProfileGroup :
        BottomNavigationScreen(
            route = "profile_group",
            resourceId = R.string.profile,
            icon = Icons.Rounded.Person
        )

}
