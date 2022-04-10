package com.example.gamifyliving.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
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
import com.example.gamifyliving.presentation.navigation.Navigation
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(
        ExperimentalMaterialApi::class,
        ExperimentalComposeUiApi::class
    )
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
                    Box(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Navigation(
                            navController = navController,
                            setBottomBarVisibility = {
                                isBottomNavBarVisible.value = it
                            }
                        )
                    }
                }

            }

        }
    }
}