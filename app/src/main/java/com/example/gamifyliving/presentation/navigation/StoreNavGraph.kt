package com.example.gamifyliving.presentation.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
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
                openEditStoreItemPage = { storeItem ->
                    navController.navigate("${Screen.EditStoreItem.route}/${storeItem.uid}")
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
        composable(
            "${Screen.EditStoreItem.route}/{store_item_id}",
            arguments = listOf(navArgument("store_item_id") { type = NavType.IntType })
        ) {
            setBottomBarVisibility(Screen.EditStoreItem.hasBottomNavBar)
            EditStoreItemHandler()
        }
    }

}
