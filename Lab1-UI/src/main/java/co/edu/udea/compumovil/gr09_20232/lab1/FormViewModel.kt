package co.edu.udea.compumovil.gr09_20232.lab1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class FormUIState(
    val personalInformationName: String = "",
    val personalInformationLastName: String = "",
    val personalInformationGender: Gender? = null,
    val personalInformationBirthday: String = "",
    val personalInformationEducationLevel: EducationLevel? = null,
    val contactInformationPhone: String = "",
    val contactInformationAddress: String = "",
    val contactInformationMail: String = "",
    val contactInformationCountry: String = "",
    val contactInformationCity: String = "",
)

class FormViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FormUIState())
    val uiState: StateFlow<FormUIState> = _uiState.asStateFlow()

    fun setPersonalInformationName(name: String) {
        _uiState.update { currentState ->
            currentState.copy(
                personalInformationName = name
            )
        }
    }

    fun setPersonalInformationLastName(lastName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                personalInformationLastName = lastName
            )
        }
    }

    fun setPersonalInformationGender(gender: Gender) {
        _uiState.update { currentState ->
            currentState.copy(
                personalInformationGender = gender
            )
        }
    }

    fun setPersonalInformationBirthday(birthday: String) {
        _uiState.update { currentState ->
            currentState.copy(
                personalInformationBirthday = birthday
            )
        }
    }

    fun setPersonalInformationEducationLevel(educationLevel: EducationLevel) {
        _uiState.update { currentState ->
            currentState.copy(
                personalInformationEducationLevel = educationLevel
            )
        }
    }

    fun setContactInformationPhone(phone: String) {
        _uiState.update { currentState ->
            currentState.copy(
                contactInformationPhone = phone
            )
        }
    }

    fun setContactInformationAddress(address: String) {
        _uiState.update { currentState ->
            currentState.copy(
                contactInformationAddress = address
            )
        }
    }

    fun setContactInformationMail(mail: String) {
        _uiState.update { currentState ->
            currentState.copy(
                contactInformationMail = mail
            )
        }
    }

    fun setContactInformationCountry(country: String) {
        _uiState.update { currentState ->
            currentState.copy(
                contactInformationCountry = country
            )
        }
    }

    fun setContactInformationCity(city: String) {
        _uiState.update { currentState ->
            currentState.copy(
                contactInformationCity = city
            )
        }
    }
}