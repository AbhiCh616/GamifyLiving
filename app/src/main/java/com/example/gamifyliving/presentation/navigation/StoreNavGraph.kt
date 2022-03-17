package com.example.gamifyliving.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.gamifyliving.presentation.add_store_item.AddStoreItemHandler
import com.example.gamifyliving.presentation.edit_store_item.EditStoreItemHandler
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
                },
                openEditStoreItemPage = {
                    navController.navigate(Screen.EditStoreItem.route)
                }
            )
        }
        composable(Screen.AddStoreItem.route) {
            setBottomBarVisibility(Screen.AddStoreItem.hasBottomNavBar)
            AddStoreItemHandler(
                onClose = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.EditStoreItem.route) {
            setBottomBarVisibility(Screen.EditStoreItem.hasBottomNavBar)
            EditStoreItemHandler()
        }
    }

}
