package com.example.gamifyliving.presentation.component

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.R
import com.example.gamifyliving.presentation.theme.Shapes
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

@Composable
fun AppDatePicker(
    dateText: String?,
    updateDate: (LocalDate?) -> Unit,
    modifier: Modifier = Modifier
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
                showDatePicker(
                    activity = activity,
                    updateDate = updateDate
                )
            }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(dateText ?: stringResource(id = R.string.nothing_selected))
        Icon(
            Icons.Rounded.DateRange,
            contentDescription = null
        )
    }
}

private fun showDatePicker(
    activity: AppCompatActivity,
    updateDate: (LocalDate?) -> Unit
) {
    val datePickerBuilder = MaterialDatePicker.Builder.datePicker()
    val datePicker = datePickerBuilder.build()
    datePicker.show(activity.supportFragmentManager, datePicker.toString())
    datePicker.addOnPositiveButtonClickListener {
        val date = LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault())
        updateDate(date.toLocalDate())
    }
}
