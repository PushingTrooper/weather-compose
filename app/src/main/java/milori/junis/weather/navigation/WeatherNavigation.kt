package milori.junis.weather.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import milori.junis.weather.ui.screens.WeatherScreen

@Composable
fun WeatherNavigation(
    navController: NavHostController,
    innerPadding: PaddingValues,
    snackbarHostState: SnackbarHostState
) {
    NavHost(navController = navController, startDestination = WeatherScreens.WeatherScreen.name) {
        composable(WeatherScreens.WeatherScreen.name) {
            WeatherScreen(innerPadding)
        }
    }
}