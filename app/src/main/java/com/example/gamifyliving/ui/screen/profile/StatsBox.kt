package com.example.gamifyliving.ui.screen.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
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
    stats: List<Stat>?,
    inEditMode: Boolean
) {
    Column(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(text = stringResource(id = R.string.stats), style = MaterialTheme.typography.h5)
        Spacer(modifier = Modifier.height(16.dp))
        StatsList(stats, inEditMode)
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

@Composable
fun StatsEditButton() {
    Button(
        onClick = {},
        shape = RoundedCornerShape(50)
    ) {
        Icon(
            Icons.Filled.Edit,
            contentDescription = stringResource(R.string.edit),
            modifier = Modifier.size(ButtonDefaults.IconSize)
        )
        Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.edit))
    }
}