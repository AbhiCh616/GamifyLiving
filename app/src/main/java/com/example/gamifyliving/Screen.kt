package com.example.gamifyliving

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector
    ) {
    object Home : Screen("home", R.string.home, Icons.Rounded.Home)
    object Profile : Screen("profile", R.string.profile, Icons.Rounded.Person)
    object Tasks : Screen("tasks", R.string.tasks, Icons.Rounded.CheckCircle)
    object Rewards : Screen("rewards", R.string.rewards, Icons.Rounded.Star)
}
