package com.example.gamifyliving

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.gamifyliving.ui.component.AddTaskFAB
import com.example.gamifyliving.ui.component.BottomNavigationBar
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.util.Navigation
import com.example.gamifyliving.util.Screen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamifyLivingTheme {
                val navController = rememberNavController()
                val items = listOf(
                    Screen.Home,
                    Screen.Tasks,
                    Screen.Home, // For FAB space
                    Screen.Rewards,
                    Screen.Profile,
                )
                Scaffold (
                    floatingActionButton = { AddTaskFAB() },
                    isFloatingActionButtonDocked = true,
                    floatingActionButtonPosition = FabPosition.Center,
                    bottomBar = { BottomNavigationBar(items = items, navController = navController) }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}