package co.edu.udea.compumovil.gr09_20232.lab1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class PersonalInformationScreen(@StringRes val title: Int) {
    PersonalInformation(title = R.string.personal_information),
    ContactInformation(title = R.string.contact_information),
}


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
        Router()
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Router() {
        val formViewModel = FormViewModel()
        val navController: NavHostController = rememberNavController()
        val onNextClicked =  {
            navController.navigate(PersonalInformationScreen.ContactInformation.name)
        }
        NavHost(
            navController = navController,
            startDestination = PersonalInformationScreen.PersonalInformation.name,
        ) {
            composable(route = PersonalInformationScreen.PersonalInformation.name) {
                PersonalInformation(formViewModel, onNextButtonClicked = onNextClicked)
            }
            composable(route = PersonalInformationScreen.ContactInformation.name) {
                SecondMainContent(formViewModel)
            }
        }
    }

}




