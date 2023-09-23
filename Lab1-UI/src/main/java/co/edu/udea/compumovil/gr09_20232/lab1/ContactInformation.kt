package co.edu.udea.compumovil.gr09_20232.lab1


import android.content.res.Configuration
import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import java.util.Locale

@ExperimentalMaterial3Api
class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondMainContent()


        }
    }
}
@ExperimentalMaterial3Api
@Composable
fun SecondMainContent(){
    //strings
    val titleContactInformation = stringResource(id = R.string.title_contact_information)
    val phoneContact = stringResource(id = R.string.phone_contact)
    val emailContact = stringResource(id = R.string.email_contact)
    val directionContact = stringResource(id = R.string.direction_contact)

    Column{
        val config = LocalConfiguration.current
        val potraitMode = remember{ mutableStateOf(config.orientation) }

        var selectedLanguage by remember { mutableStateOf(ChooseLenguaje.Spanish) }

        
        if(potraitMode.value == Configuration.ORIENTATION_PORTRAIT){
            HeaderWithTitle(title = titleContactInformation)
            FormElement(label = phoneContact, inputType = InputType.Number)
            FormElement(label = emailContact, inputType = InputType.Email)
            LocatationDownMenu()
            FormElement(label = directionContact, inputType = InputType.Number)
        }else {
            Column(){
                Text(text="holi")
            }
        }

        ChooseLenguajeDropDown(setChooseLenguaje = { selectedLanguage ->
            // Aquí puedes manejar la selección del idioma
            when (selectedLanguage) {
                ChooseLenguaje.English -> {

                }
                ChooseLenguaje.Spanish -> {

                }
            }
        })
    }

}


@Composable
fun RenderResourse(idioma:String){
    val resource = LocalContext.current.resources

    val newConfig = Configuration(resource.configuration)
    newConfig.setLocale(Locale(idioma))

    resource.updateConfiguration(newConfig, resource.displayMetrics)


}
