package milori.junis.weather

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.datastore.dataStore
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import milori.junis.weather.data.data_store.AppSettingsSerializable
import milori.junis.weather.navigation.WeatherNavigation
import milori.junis.weather.ui.theme.WeatherTheme

val Context.dataStore by dataStore("app-settings.json", AppSettingsSerializable)

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                WeatherApp()
            }
        }
    }

    @Composable
    fun WeatherApp() {
        val navController = rememberNavController()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState, modifier = Modifier.imePadding())
            }
        ) { innerPadding ->
            WeatherNavigation(
                navController = navController,
                innerPadding = innerPadding,
                snackbarHostState = snackbarHostState
            )
        }
    }
}