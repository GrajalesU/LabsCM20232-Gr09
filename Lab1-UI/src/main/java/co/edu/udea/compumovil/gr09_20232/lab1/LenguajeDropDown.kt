package co.edu.udea.compumovil.gr09_20232.lab1

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
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

enum class ChooseLenguaje {
    English,
    Spanish,
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseLenguajeDropDown(setChooseLenguaje: (ChooseLenguaje) -> Unit = {}) {
    var mContext = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf(ChooseLenguaje.Spanish.name) }
    val items = ChooseLenguaje.values().toList()
    val chooseLenguaje = stringResource(id = R.string.lenguaje_choose)

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    //drop countries
    val icon = if (expanded){
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
            label = { Text(text = chooseLenguaje) },
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
            items.forEach { label ->
                DropdownMenuItem(text = { Text(text = label.name) }, onClick = {
                    selectedItem = label.name
                    expanded = false
                    Toast.makeText(mContext, "  $selectedItem", Toast.LENGTH_SHORT).show()

                })
            }

        }
    }

}

