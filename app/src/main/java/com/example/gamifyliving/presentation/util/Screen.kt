package com.example.gamifyliving.presentation.util

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.gamifyliving.R

sealed class Screen(
    val route: String,
    val hasBottomNavBar: Boolean = false
) {

    object Stats : Screen(route = "stats", hasBottomNavBar = true)
    object Tasks : Screen(route = "tasks", hasBottomNavBar = true)
    object Store : Screen(route = "store", hasBottomNavBar = true)
    object Profile : Screen(route = "profile", hasBottomNavBar = true)
    object AddStat : Screen(route = "add_stat")
    object EditStat : Screen(route = "edit_stat")
    object AddTask : Screen(route = "add_task")
    object EditTask : Screen(route = "edit_task")
    object AddStoreItem : Screen(route = "add_store_item")

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

    object TaskGroup :
        BottomNavigationScreen(
            route = "task_group",
            resourceId = R.string.tasks,
            icon = Icons.Rounded.CheckCircle,
            hasBottomNavBar = true
        )

    object StoreGroup :
        BottomNavigationScreen(
            route = "store_group",
            resourceId = R.string.store,
            icon = Icons.Rounded.ShoppingCart,
            hasBottomNavBar = true
        )

    object ProfileGroup :
        BottomNavigationScreen(
            route = "profile_group",
            resourceId = R.string.profile,
            icon = Icons.Rounded.Person
        )

}
