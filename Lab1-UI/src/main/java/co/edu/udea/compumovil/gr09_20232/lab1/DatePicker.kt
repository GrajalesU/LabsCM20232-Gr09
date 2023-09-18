package co.edu.udea.compumovil.gr09_20232.lab1

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import java.util.*
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
fun CustomDatePicker(text: String, setValue: (String) -> Unit = {}) {
    val mContext = LocalContext.current

    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()


    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDate = remember {
        mutableStateOf("")
    }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
            setValue("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, mYear, mMonth, mDay
    )

    val buttonText = mDate.value.ifEmpty { text }
    val buttonBgColor =
        if (mDate.value.isNotEmpty()) Color.Transparent else MaterialTheme.colorScheme.primary
    val buttonColor: Color = if (mDate.value.isNotEmpty()) Color.Black else Color.White

    Button(
        onClick = {
            mDatePickerDialog.show()
        },
        colors = ButtonDefaults.buttonColors(containerColor = buttonBgColor),
    ) {
        Text(text = buttonText, color = buttonColor)
    }
}

