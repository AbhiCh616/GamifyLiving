package com.example.gamifyliving.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gamifyliving.util.navigation.Screen

@Composable
fun BottomNavigationBar(
    items: List<Screen>,
    navController: NavController
) {
    BottomAppBar(cutoutShape = RoundedCornerShape(50)) {
        BottomNavigation {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            items.forEachIndexed { index, screen ->
                if (index != 2) {
                    BottomNavigationItem(
                        icon = { Icon(screen.icon!!, contentDescription = null) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                } else {
                    BottomNavigationItem(
                        icon = { },
                        selected = false,
                        onClick = { }
                    )
                }
            }
        }
    }
}