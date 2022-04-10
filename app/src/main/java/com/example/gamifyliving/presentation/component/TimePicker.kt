package com.example.gamifyliving.presentation.component

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.presentation.theme.Shapes
import com.google.android.material.timepicker.MaterialTimePicker
import java.time.LocalTime

@Composable
fun AppTimePicker(
    timeText: String?,
    updateTime: (LocalTime?) -> Unit,
    displayWhenNotSelected: String,
    modifier: Modifier = Modifier,
    initialTime: LocalTime? = null,
) {
    val activity = LocalContext.current as AppCompatActivity

    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.onSurface,
                shape = Shapes.medium
            )
            .clickable {
                showTimePicker(
                    activity = activity,
                    initialTime = initialTime,
                    updateTime = updateTime
                )
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(timeText ?: displayWhenNotSelected)
    }
}

private fun showTimePicker(
    activity: AppCompatActivity,
    initialTime: LocalTime? = null,
    updateTime: (LocalTime) -> Unit
) {
    val timePickerBuilder = MaterialTimePicker.Builder().apply {
        initialTime?.let {
            setHour(it.hour).setMinute(it.minute)
        }
    }
    val timePicker = timePickerBuilder.build()
    timePicker.show(activity.supportFragmentManager, timePicker.toString())
    timePicker.addOnPositiveButtonClickListener {
        val time = LocalTime.of(timePicker.hour, timePicker.minute)
        updateTime(time)
    }
}
