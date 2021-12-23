package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gamifyliving.R
import com.example.gamifyliving.data.model.Stat
import com.example.gamifyliving.util.navigation.Screen

@Composable
fun StatsBox(
    navController: NavController,
    stats: List<Stat>
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(text = stringResource(id = R.string.stats), style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        stats.take(3).forEach { IndividualStat(it) }
        Spacer(modifier = Modifier.height(12.dp))
        ViewAllButton(navController)
    }
}

@Composable
fun ViewAllButton(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = {
            navController.navigate(Screen.Stats.route) {
                launchSingleTop = true
            }
        }) {
            Text(text = stringResource(id = R.string.view_all))
        }
    }
}