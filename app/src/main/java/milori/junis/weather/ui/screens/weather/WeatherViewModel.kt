package milori.junis.weather.ui.screens.weather

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import milori.junis.weather.data.WeatherRepository
import milori.junis.weather.data.model.forecast_16_days.WeatherForecast
import milori.junis.weather.utils.toStringDateTime
import java.time.LocalDateTime
import javax.inject.Inject
import milori.junis.weather.R
import milori.junis.weather.data.UiState
import milori.junis.weather.data.helpers.Either
import milori.junis.weather.data.helpers.NetworkResponse
import java.util.Locale
import kotlin.time.TimeSource

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {
    private val appUiState = MutableStateFlow(UiState())
    val uiState = appUiState.asStateFlow()

    val currentWeatherTemperature = mutableStateOf("-")
    val timeOfLatestCall = mutableStateOf("-")
    val dayOfLatestCall = mutableStateOf("-")
    val city = mutableStateOf("-")
    val weatherIconRes = mutableIntStateOf(R.drawable.clear_day)
    val weatherForecast = mutableStateListOf<List<WeatherForecast>>()

//    val errorFlow =

    init {
        callWeather()
    }

    private fun callWeather() {
        val now = LocalDateTime.now()
        timeOfLatestCall.value = now.toStringDateTime("hh:mma")
        dayOfLatestCall.value = now.toStringDateTime("EEEE, dd MMM")

        viewModelScope.launch {
            when (val response = repository.getWeather(41.324665368, 19.818663392)) {
                is NetworkResponse.Success -> {
                    val body = response.body
                    val weather = body.weather.first()

                    val weatherCode = weather.id
                    weatherIconRes.intValue = if (weatherCode == 800) {
                        R.drawable.clear_day
                    } else {
                        when (weatherCode / 100) {
                            2 -> R.drawable.thunderstorm
                            3 -> R.drawable.rainy_light
                            5 -> R.drawable.rainy
                            6 -> R.drawable.weather_snowy
                            7 -> R.drawable.mist
                            8 -> R.drawable.cloud
                            else -> R.drawable.clear_day
                        }
                    }
                    city.value = body.name
                    currentWeatherTemperature.value =
                        String.format(Locale.getDefault(), "%.0f", body.main.temp)
                }

                is NetworkResponse.ApiError -> {
                    appUiState.update {
                        it.copy(
                            showSnackBarEvent = TimeSource.Monotonic.markNow(),
                            message = Either.Right(response.body?.message.orEmpty())
                        )
                    }
                }

                is NetworkResponse.NetworkError -> {
                    appUiState.update {
                        it.copy(
                            showSnackBarEvent = TimeSource.Monotonic.markNow(),
                            message = Either.Left(R.string.error_network)
                        )
                    }
                }

                is NetworkResponse.UnknownError -> {
                    appUiState.update {
                        it.copy(
                            showSnackBarEvent = TimeSource.Monotonic.markNow(),
                            message = Either.Left(R.string.error_unknown)
                        )
                    }
                }

                else -> {
                    appUiState.update {
                        it.copy(
                            showSnackBarEvent = TimeSource.Monotonic.markNow(),
                            message = Either.Left(R.string.error_unknown)
                        )
                    }
                }
            }
        }
    }
}