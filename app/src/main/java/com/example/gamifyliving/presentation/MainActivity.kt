package com.example.gamifyliving.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.gamifyliving.presentation.component.BottomNavigationBar
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme
import com.example.gamifyliving.presentation.navigation.Navigation

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamifyLivingTheme {
                val navController = rememberNavController()
                val isBottomNavBarVisible = rememberSaveable { (mutableStateOf(false)) }

                Scaffold(
                    bottomBar = {
                        if (isBottomNavBarVisible.value) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        Navigation(navController = navController, setBottomNavBar = {
                            isBottomNavBarVisible.value = it
                        })
                    }
                }
            }
        }
    }
}