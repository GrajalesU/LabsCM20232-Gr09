package co.edu.udea.compumovil.gr09_20232.lab1

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

enum class InputType {
    Text,
    Number,
    Email,
    Password
}

@Composable
fun FormElement(
    label: String,
    inputType: InputType = InputType.Text,
    setValue: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge)
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                setValue(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            keyboardOptions = KeyboardOptions.Default.copy(

                keyboardType = when (inputType) {
                    InputType.Text -> KeyboardType.Text
                    InputType.Number -> KeyboardType.Number
                    InputType.Email -> KeyboardType.Email
                    InputType.Password -> KeyboardType.Password
                },
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences,
                autoCorrect = false
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
                            setValue(newText)
                        } else {
                            text = newText
                            setValue(newText)
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

