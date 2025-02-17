package milori.junis.weather.ui.screens.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import milori.junis.weather.R
import milori.junis.weather.data.UiState
import milori.junis.weather.data.helpers.Either
import milori.junis.weather.navigation.WeatherScreens
import milori.junis.weather.utils.LatAndLong
import milori.junis.weather.utils.RequestLocationPermission
import milori.junis.weather.utils.getLastUserLocation
import kotlin.time.TimeMark
import kotlin.time.TimeSource

@Composable
fun WeatherScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    // Request location permission using a Compose function
    RequestLocationPermission(
        onPermissionGranted = {
            getLastUserLocation(
                context,
                onGetLocationSuccess = {
                    viewModel.getWeatherFromLocation(LatAndLong(it.first, it.second))
                },
                onGetLocationFailed = { exception ->
                    viewModel.changeUiState(
                        UiState(
                            TimeSource.Monotonic.markNow(),
                            Either.Right(exception.message.orEmpty())
                        )
                    )
                }
            )
        },
        onPermissionDenied = {
            viewModel.changeUiState(
                UiState(
                    TimeSource.Monotonic.markNow(),
                    Either.Left(R.string.permission_data_denied)
                )
            )
        }
    )

    LaunchedEffect(uiState.showSnackBarEvent) {
        if (uiState.showSnackBarEvent != null) {
            val message = when (uiState.message) {
                is Either.Left<Int> -> {
                    context.getString((uiState.message as Either.Left<Int>).value)
                }

                is Either.Right -> {
                    (uiState.message as Either.Right<String>).value
                }

                else -> {
                    context.getString(R.string.error_unknown)
                }
            }
            snackbarHostState.showSnackbar(message)
        }
    }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.CenterEnd) {
            Text(
                viewModel.dayOfLatestCall.value,
                Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.W300,
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = {
                    navController.navigate(WeatherScreens.SettingsScreen.name)
                },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_settings),
                    contentDescription = stringResource(
                        id = R.string.settings
                    )
                )
            }
        }
        Text(
            viewModel.timeOfLatestCall.value,
            fontSize = 40.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
        Text(viewModel.city.value, Modifier.padding(top = 12.dp), fontWeight = FontWeight.W300)
        Column(
            Modifier
                .weight(1f)
                .padding(top = 16.dp, bottom = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = viewModel.weatherIconRes.intValue),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                modifier = Modifier
                    .fillMaxWidth(0.6f)
            )
            Text(
                text = "${viewModel.currentWeatherTemperature.value}°",
                fontSize = 85.sp,
                fontWeight = FontWeight.W300
            )
        }
        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalDivider(Modifier.fillMaxWidth(0.7f), thickness = 2.dp)
        }
    }
}
