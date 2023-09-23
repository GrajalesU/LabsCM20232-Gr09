package co.edu.udea.compumovil.gr09_20232.lab1

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import androidx.compose.ui.text.intl.Locale
import java.util.Locale as JavaLocale


    object LocaleHelper {
        fun changeLocale(context: Context, newLocale: Locale) {
            val resources = context.resources
            val configuration = Configuration(resources.configuration)
            configuration.setLocale(newLocale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }

        private fun Configuration.setLocale(newLocale: Locale) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                setLocale(newLocale)
            } else {
                // En versiones anteriores a JELLY_BEAN_MR1, use localeCompat
                var localeCompat = newLocale
            }
        }
    }


