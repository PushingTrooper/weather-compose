package milori.junis.weather.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import milori.junis.weather.ui.screens.settings.SettingsScreen
import milori.junis.weather.ui.screens.weather.WeatherScreen

@Composable
fun WeatherNavigation(
    navController: NavHostController,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.WeatherScreen.name,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(WeatherScreens.WeatherScreen.name) {
            WeatherScreen(navController, snackbarHostState)
        }
        composable(WeatherScreens.SettingsScreen.name) {
            SettingsScreen(navController, snackbarHostState)
        }
    }
}