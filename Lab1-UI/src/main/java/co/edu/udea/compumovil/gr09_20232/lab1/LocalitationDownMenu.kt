package co.edu.udea.compumovil.gr09_20232.lab1

import android.util.Log
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@ExperimentalMaterial3Api
@Composable
fun LocalitationDownMenu(selectItem: String){
    var selectedItem by remember { mutableStateOf(" ")  }
    var countries: List<CountryData>? by remember { mutableStateOf(null) }
    var cities: List<CountryData>? by remember { mutableStateOf(null) }


    var expandedCountries by remember { mutableStateOf(false) }
    var expandedCities by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val iconCountries = if (expandedCountries){
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

    //drop down menu countries
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
            label = { Text(text = selectItem) },
            textStyle = LocalTextStyle.current.copy(
                color = LocalContentColor.current
            ),
            trailingIcon = {
                Icon(iconCountries, "", Modifier
                    .clickable { expandedCountries = !expandedCountries })
            }
        )

        DropdownMenuCountry(
            expandedCountries = expandedCountries,
            onDismissRequest = { expandedCountries = false},
            modColumn (modifier = Modifier.padding(20.dp)){

        OutlinedTextField(
            value = selectedItem,
            onValueChange = {selectedItem = it},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = selectItem) },
            textStyle = LocalTextStyle.current.copy(
                color = LocalContentColor.current
            ),
            trailingIcon = {
               ifier = Modifier
                .width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {
            //Log.d("API", "Lista: "+countries.toString())
            countries?.forEach { label ->
                DropdownMenuItemCountry(text = { Text(text = label.countryName) }, onClick = {
                    selectedItem = label.countryName
                    expandedCountries = false
                    cities = label.citiesName
                })
            }

        }
    

        
        //drop down menu cities
        val iconCities = if (expandedCities){
            Icons.Filled.KeyboardArrowUp
        }else{
            Icons.Filled.KeyboardArrowDown
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
                label = { Text(text = selectItem) },
                textStyle = LocalTextStyle.current.copy(
                    color = LocalContentColor.current
                ),
                trailingIcon = {
                    Icon(iconCities, "", Modifier
                        .clickable { expandedCities = !expandedCities})
                }
            )
    
            DropdownMenuCities(
                expandedCities = expandedCities,
                onDismissRequest = { expandedCities = false},
                modColumn (modifier = Modifier.padding(20.dp)){
    
            OutlinedTextField(
                value = selectedItem,
                onValueChange = {selectedItem = it},
                readOnly = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    },
                label = { Text(text = selectItem) },
                textStyle = LocalTextStyle.current.copy(
                    color = LocalContentColor.current
                ),
                trailingIcon = {
                   ifier = Modifier
                    .width(with(LocalDensity.current){textFieldSize.width.toDp()})
            ) {
                //Log.d("API", "Lista: "+countries.toString())
                cities?.forEach { label ->
                    DropdownMenuItemCities(text = { Text(text = label.countryName) }, onClick = {
                        selectedItem = label.countryName
                        expandedCities = false
                    })
                }
    
            }


    }
    Log.d("pais seleccionado",selectedItem)



}