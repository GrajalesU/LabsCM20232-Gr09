package co.edu.udea.compumovil.gr09_20232.lab1

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

enum class Gender {
    Male,
    Female,
    Other
}

@Composable
fun GenderSelector(setGender: (Gender) -> Unit = {}) {
    var selectedGender: Gender? by remember { mutableStateOf(null) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = stringResource(R.string.gender), style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selectedGender == Gender.Male,
                onClick = {
                    selectedGender = Gender.Male
                    setGender(Gender.Male)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(text = Gender.Male.name)

            RadioButton(
                selected = selectedGender == Gender.Female,
                onClick = {
                    selectedGender = Gender.Female
                    setGender(Gender.Female)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(text = Gender.Female.name)

            RadioButton(
                selected = selectedGender == Gender.Other,
                onClick = {
                    selectedGender = Gender.Other
                    setGender(Gender.Other)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(text = Gender.Other.name)
        }
    }
}
