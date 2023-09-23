package co.edu.udea.compumovil.gr09_20232.lab1

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@ExperimentalMaterial3Api
@Composable
fun LocatationDownMenu(){
    var selectedItemCountries by remember { mutableStateOf(" ")  }
    var selectedItemCities by remember { mutableStateOf(" ")  }
    var countries: List<CountryData>? by remember { mutableStateOf(null) }
    var cities: List<String>? by remember { mutableStateOf(null) }
    var mContext = LocalContext.current

    //strings
    val selectCountryLabel = stringResource(id = R.string.select_country_label)
    val selectCityLabel = stringResource(id = R.string.select_city_label)

    var expandedCountries by remember { mutableStateOf(false) }
    var expandedCities by remember { mutableStateOf(false) }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }




    //cargar datos del mock
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

    //drop countries
    val icon = if (expandedCountries){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column (modifier = Modifier.padding(20.dp)){

        OutlinedTextField(
            value = selectedItemCountries,
            onValueChange = {selectedItemCountries = it},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = selectCountryLabel) },
            textStyle = LocalTextStyle.current.copy(
                color = LocalContentColor.current
            ),
            trailingIcon = {
                Icon(icon, "", Modifier
                    .clickable { expandedCountries = !expandedCountries })
            }
        )

        DropdownMenu(
            expanded = expandedCountries,
            onDismissRequest = { expandedCountries = false},
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            //Log.d("API", "Lista: "+countries.toString())
            countries?.forEach { label ->
                DropdownMenuItem(text = { Text(text = label.countryName) }, onClick = {
                    selectedItemCountries = label.countryName
                    expandedCountries = false
                    Toast.makeText(mContext, "  $selectedItemCountries", Toast.LENGTH_SHORT).show()
                    cities = label.citiesName
                })
            }

        }
    }
    //Log.d("pais seleccionado",selectedItem)


    //drop cities
    val iconCountryDown = if (expandedCities){
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column (modifier = Modifier.padding(20.dp)){

        OutlinedTextField(
            value = selectedItemCities,
            onValueChange = {selectedItemCities = it},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = selectCityLabel) },
            textStyle = LocalTextStyle.current.copy(
                color = LocalContentColor.current
            ),
            trailingIcon = {
                Icon(icon, "", Modifier
                    .clickable { expandedCities = !expandedCities })
            }
        )

        DropdownMenu(
            expanded = expandedCities,
            onDismissRequest = { expandedCities = false},
            modifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            //Log.d("API", "Lista: "+countries.toString())
            cities?.forEach { label ->
                DropdownMenuItem(text = { Text(text = label) }, onClick = {
                    selectedItemCities = label
                    Toast.makeText(mContext, "  $selectedItemCities", Toast.LENGTH_SHORT).show()
                    expandedCities = false
                })
            }

        }
    }
}