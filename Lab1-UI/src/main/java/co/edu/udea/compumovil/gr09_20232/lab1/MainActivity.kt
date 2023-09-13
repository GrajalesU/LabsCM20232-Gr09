package co.edu.udea.compumovil.gr09_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainContent()
        }
}

@Composable
fun MainContent(){
    Column{
        HeaderWithTitle(title = "Personal Information")
        FormElement(label = "Name", inputType = InputType.Text)
        FormElement(label = "Last Name", inputType = InputType.Text)
        GenderSelector()
        BirthdaySelector()
        EducationLevelSpinner()
    }

}
}

@Composable
fun HeaderWithTitle(title: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
}

enum class InputType {
    Text,
    Number,
    Email,
    Password
}

@Composable
fun FormElement(label: String, inputType: InputType = InputType.Text) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)

        BasicTextField(
            value = text,
            onValueChange = { newText -> text = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
            ,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = when (inputType) {
                    InputType.Text -> KeyboardType.Text
                    InputType.Number -> KeyboardType.Number
                    InputType.Email -> KeyboardType.Email
                    InputType.Password -> KeyboardType.Password
                }
            ),
            textStyle = LocalTextStyle.current.copy(
                color = LocalContentColor.current
            ),
            decorationBox = { innerTextField ->
                BasicTextField(
                    value = text,
                    onValueChange = { newText ->
                        if (inputType == InputType.Password) {
                            // Mask the password input
                            text = "*".repeat(newText.length)
                        } else {
                            text = newText
                        }
                    },
                    textStyle = TextStyle(color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    innerTextField()
                }
            }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.Gray)
        )
    }
}


enum class Gender {
    Male,
    Female,
    Other
}

@Composable
fun GenderSelector() {

    var selectedGender by remember { mutableStateOf(Gender.Male) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Gender", style = MaterialTheme.typography.bodyLarge)

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RadioButton(
                selected = selectedGender == Gender.Male,
                onClick = { selectedGender = Gender.Male },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(text = "Male")

            RadioButton(
                selected = selectedGender == Gender.Female,
                onClick = { selectedGender = Gender.Female },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(text = "Female")

            RadioButton(
                selected = selectedGender == Gender.Other,
                onClick = { selectedGender = Gender.Other },
                colors = RadioButtonDefaults.colors(
                    selectedColor = MaterialTheme.colorScheme.primary
                )
            )
            Text(text = "Other")
        }
    }
}

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

enum class EducationLevel {
    ElementarySchool,
    MiddleSchool,
    HighSchool,
    College,
    University
}

@Composable
fun EducationLevelSpinner() {
    var selectedLevel by remember { mutableStateOf(EducationLevel.ElementarySchool) }
    var expanded by remember { mutableStateOf(false) }

    val levels = EducationLevel.values()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
    ) {
        BasicTextField(
            value = selectedLevel.name,
            onValueChange = {},
            textStyle = LocalTextStyle.current.copy(
                color = LocalContentColor.current,
                fontSize = 16.sp
            ),
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            levels.forEach { level ->
                DropdownMenuItem(
                    onClick = {
                        selectedLevel = level
                        expanded = false
                    },
                    text = { level.name }
                )
            }
        }
    }
}

// TODO: Pasar las funciones a archivos
// TODO: Terminar formulario





