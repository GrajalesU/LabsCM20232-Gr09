package co.edu.udea.compumovil.gr09_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            MainContent()
        }
}

@Composable
fun MainContent(){
    Column(modifier = Modifier.verticalScroll(rememberScrollState()).fillMaxHeight()){
        HeaderWithTitle(title = "Personal Information")
        FormElement(label = "Name", inputType = InputType.Text)
        FormElement(label = "Last Name", inputType = InputType.Text)
        GenderSelector()
        BirthdaySelector()
        EducationLevelDropDown()
        Button(
            onClick = {
            },
            modifier = Modifier
                .width(300.dp)
                .padding(16.dp)
                .align(Alignment.End)
        ) {
            Text(text = "Next")
        }
        }
    }
}

// TODO: add context
