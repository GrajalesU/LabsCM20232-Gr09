package co.edu.udea.compumovil.gr09_20232.lab1

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.edu.udea.compumovil.gr09_20232.lab1.CustomDatePicker

@Composable
fun BirthdaySelector() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Birthday", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.width(60.dp))
        CustomDatePicker("Select a date")
    }
}

