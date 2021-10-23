package com.example.gamifyliving

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.gamifyliving.ui.theme.GamifyLivingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamifyLivingTheme {
                val navController = rememberNavController()
                val items = listOf(
                    Screen.Home,
                    Screen.Profile
                )
                Scaffold (
                    bottomBar = { BottomNavigationBar(items = items, navController = navController) }
                ) {
                    Navigation(navController = navController)
                }
            }
        }
    }
}