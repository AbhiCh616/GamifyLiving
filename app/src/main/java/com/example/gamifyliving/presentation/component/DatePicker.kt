package com.example.gamifyliving.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gamifyliving.presentation.theme.GamifyLivingTheme
import com.example.gamifyliving.presentation.theme.Shapes
import com.google.android.material.datepicker.MaterialDatePicker

@Composable
fun AppDatePicker(
    dateText: String,
    updateDate: (Long?) -> Unit,
    modifier: Modifier = Modifier
) {
    val activity = LocalContext.current as AppCompatActivity

    Row(
        modifier = modifier
            .fillMaxWidth()
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
        Text(dateText)
        Icon(
            Icons.Rounded.DateRange,
            contentDescription = null
        )
    }
}

private fun showDatePicker(
    activity: AppCompatActivity,
    updateDate: (Long?) -> Unit
) {
    val datePicker = MaterialDatePicker.Builder.datePicker().build()
    datePicker.show(activity.supportFragmentManager, datePicker.toString())
    datePicker.addOnPositiveButtonClickListener(updateDate)
}

@Preview(
    name = "Night Mode",
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
fun AppDatePickerPreview() {
    GamifyLivingTheme {
        AppDatePicker(
            dateText = "Not Selected",
            updateDate = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
