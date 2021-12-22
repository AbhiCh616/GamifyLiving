package com.example.gamifyliving.ui.screen.rewards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gamifyliving.ui.component.BottomNavigationBar
import com.example.gamifyliving.ui.component.bottomNavigationItems

@Composable
fun Rewards(
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavigationItems,
                navController = navController
            )
        }
    ) {
        Surface(color = MaterialTheme.colors.background) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Rewards")
            }
        }
    }
}