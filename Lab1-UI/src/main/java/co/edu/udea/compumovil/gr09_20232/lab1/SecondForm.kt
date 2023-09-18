package co.edu.udea.compumovil.gr09_20232.lab1


import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Path


class ContactDataActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SecondMainContent()
        }
    }
}

data class CountryData(
    val country_name: String,
    val country_short_name: String,
    val country_phone_code: Int,
)

data class StatesData(
    val state_name: String,
)

data class CitiesData(
    val city_name: String,
)

interface LocalitationApi {
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyIjp7InVzZXJfZW1haWwiOiJsYXVyYS50YXNjb25AdWRlYS5lZHUuY28iLCJhcGlfdG9rZW4iOiJUVlZhTTZCMFdWRFppcllpeDUzNmd6bFdUTm1FYV9HMmtxY2laeDE1emtFV0hqNWk3SXM5OGg3MFFtVTJobUJoN2RBIn0sImV4cCI6MTY5NTAwMDYzNH0.QhluoYPA-WVpWZddl09wrhxPVBvFIA6bXa-HoE5yA0A",
        "Accept: application/json"
    )

    @GET("countries")
    suspend fun getAllCountries(): List<CountryData>

    @GET("states")
    suspend fun getAllStates(): List<StatesData>

    @GET("states/{stateName}")
    suspend fun getStateByName(@Path("stateName") stateName: String): StatesData

    @GET("cities")
    suspend fun getAllCities(): List<CitiesData>

    @GET("cities/{citiesName}")
    suspend fun getCitiesByName(@Path("citiesName") citiesName: String): CitiesData


}

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondMainContent(){

    Column{
        HeaderWithTitle(title = "Personal Information")
        FormElement(label = "Teléfono", inputType = InputType.Number)
        FormElement(label = "Correo", inputType = InputType.Email)
        dropDownMenu(selectItem = "selecciona tu país")
        FormElement(label = "Dirección", inputType = InputType.Number)
    }

}

object LocalitationApiService {
    private const val BASE_URL = "https://www.universal-tutorial.com/api/"
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val localitationApi: LocalitationApi = retrofit.create(LocalitationApi::class.java)
}


@ExperimentalMaterial3Api
@Composable
fun dropDownMenu(selectItem: String){
    var selectedItem by remember { mutableStateOf(" ")  }
    var countries: List<CountryData>? by remember { mutableStateOf(null) }


    var expanded by remember { mutableStateOf(false) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    var hasLoadedDataCountry by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!hasLoadedDataCountry) {
            withContext( context = Dispatchers.IO) {
                try {

                    val loadedCountries = LocalitationApiService.localitationApi.getAllCountries()
                    countries = loadedCountries
                    hasLoadedDataCountry = true
                    Log.d("API Response","Prueba de log")
                } catch (e: Exception) {
                    Log.e("API Error", e.message ?: "Unknown error")
                }
            }
        }
    }


    Column (modifier = Modifier.padding(20.dp)){

        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = selectItem)},
            textStyle = LocalTextStyle.current.copy(
                color = LocalContentColor.current
            ),
            trailingIcon = {
                Icon(icon, "", Modifier
                    .clickable { expanded = !expanded })
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false},
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            //Log.d("API", "Lista: "+countries.toString())
            countries?.forEach { label ->
                DropdownMenuItem(text = { Text(text = label.country_name) }, onClick = {
                    selectedItem = label.country_name
                    expanded = false
                })
            }

        }
    }


}








