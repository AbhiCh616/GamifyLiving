package com.example.gamifyliving.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.presentation.add_store_item.AddStoreItem
import com.example.gamifyliving.presentation.store.StoreHandler
import com.example.gamifyliving.presentation.util.BottomNavigationScreen
import com.example.gamifyliving.presentation.util.Screen

fun NavGraphBuilder.storeGraph(
    navController: NavHostController,
    setBottomBarVisibility: (Boolean) -> Unit
) {

    navigation(
        route = BottomNavigationScreen.StoreGroup.route,
        startDestination = Screen.Store.route
    ) {
        composable(Screen.Store.route) {
            setBottomBarVisibility(Screen.Store.hasBottomNavBar)
            StoreHandler(
                addNewStoreItem = {
                    navController.navigate(Screen.AddStoreItem.route)
                }
            )
        }
        composable(Screen.AddStoreItem.route) {
            setBottomBarVisibility(Screen.AddStoreItem.hasBottomNavBar)
            AddStoreItem()
        }
    }

}
