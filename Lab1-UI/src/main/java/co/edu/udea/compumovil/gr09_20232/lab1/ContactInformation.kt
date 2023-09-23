package co.edu.udea.compumovil.gr09_20232.lab1


import android.content.res.Configuration
import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Locale
import androidx.compose.ui.Alignment

@ExperimentalMaterial3Api
@Composable
fun SecondMainContent(viewModel: FormViewModel){
    //strings
    val titleContactInformation = stringResource(id = R.string.title_contact_information)
    val phoneContact = stringResource(id = R.string.phone_contact)
    val emailContact = stringResource(id = R.string.email_contact)
    val directionContact = stringResource(id = R.string.direction_contact)
    val uiState by viewModel.uiState.collectAsState()

    Column{
        val config = LocalConfiguration.current
        val potraitMode = remember{ mutableStateOf(config.orientation) }

        var selectedLanguage by remember { mutableStateOf(ChooseLenguaje.Spanish) }


            HeaderWithTitle(title = titleContactInformation)
            FormElement(label = phoneContact, inputType = InputType.Number, setValue = {
                viewModel.setContactInformationPhone(it)
            })
            FormElement(label = emailContact, inputType = InputType.Email, setValue = {
                viewModel.setContactInformationMail(it)
            })
            LocatationDownMenu(viewModel)
            FormElement(label = directionContact, inputType = InputType.Number, setValue = {
                viewModel.setContactInformationAddress(it)
            })
        Button(
            onClick = {
                Log.d("personal Information", "Información personal: \n" +
                        "Nombre: ${uiState.personalInformationName} ${uiState.personalInformationLastName} \n" +
                        "Género: ${uiState.personalInformationGender} \n" +
                        "Nació el ${uiState.personalInformationBirthday} \n" +
                        "Nivel educativo: ${uiState.personalInformationEducationLevel} \n" +
                        "---"+
                        "Información de contacto \n" +
                        "Telefono: ${uiState.contactInformationPhone}\n" +
                        "Dirección: ${uiState.contactInformationAddress}\n" +
                        "Email: ${uiState.contactInformationMail} \n" +
                        "País: ${uiState.contactInformationCountry} \n"+
                        "Ciudad: ${uiState.contactInformationCity} \n"

                )
            },
            modifier = Modifier
                .width(240.dp)
                .padding(16.dp)
        ) {
            Text(text = "Enviar")
        }


        
    }

}


@Composable
fun RenderResourse(idioma:String){
    val resource = LocalContext.current.resources

    val newConfig = Configuration(resource.configuration)
    newConfig.setLocale(Locale(idioma))

    resource.updateConfiguration(newConfig, resource.displayMetrics)


}
