package milori.junis.weather.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun WeatherScreen(
    innerPadding: PaddingValues,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    Column(Modifier.padding(innerPadding)) {
        Text(viewModel.text.value)
    }
}