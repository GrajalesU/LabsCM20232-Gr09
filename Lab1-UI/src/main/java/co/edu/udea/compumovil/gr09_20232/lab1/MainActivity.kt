package co.edu.udea.compumovil.gr09_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable


class MainActivity : ComponentActivity() {
    private val formViewModel = FormViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }

    @Composable
    fun MainContent() {
        PersonalInformation(formViewModel)
    }
}

// TODO: add context
