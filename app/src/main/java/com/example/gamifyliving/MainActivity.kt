package com.example.gamifyliving

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.rememberNavController
import com.example.gamifyliving.ui.theme.GamifyLivingTheme
import com.example.gamifyliving.util.navigation.Navigation

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GamifyLivingTheme {
                val navController = rememberNavController()
                Navigation(navController = navController)
            }
        }
    }
}