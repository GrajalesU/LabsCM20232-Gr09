package co.edu.udea.compumovil.gr09_20232.lab1

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun PersonalInformation(viewModel: FormViewModel, onNextButtonClicked: () -> Unit ){
    val uiState by viewModel.uiState.collectAsState()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxHeight()
        ) {
            HeaderWithTitle(title = stringResource(R.string.personal_information))
            FormElement(label = stringResource(R.string.name), inputType = InputType.Text, setValue = {
                viewModel.setPersonalInformationName(it)
            })
            FormElement(label = stringResource(R.string.last_name), inputType = InputType.Text, setValue = {
                viewModel.setPersonalInformationLastName(it)
            })
            GenderSelector(setGender = {
                viewModel.setPersonalInformationGender(it)
            })
            BirthdaySelector(setBirthday = {
                viewModel.setPersonalInformationBirthday(it)
            })
            EducationLevelDropDown(setEducationLevel = {
                viewModel.setPersonalInformationEducationLevel(it)
            })
        }
        Button(
            onClick = {
                onNextButtonClicked()
            },
            modifier = Modifier
                .width(240.dp)
                .padding(16.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(text = stringResource(R.string.next))
        }
    }
}
