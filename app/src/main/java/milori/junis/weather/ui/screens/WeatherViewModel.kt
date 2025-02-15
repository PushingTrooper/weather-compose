package milori.junis.weather.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import milori.junis.weather.data.WeatherRepository
import milori.junis.weather.data.helpers.NetworkResponse
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {
    val text = mutableStateOf("")

    init {
        callWeather()
    }

    fun callWeather() {
        viewModelScope.launch {
            text.value = when(val response = repository.getWeather(41.324665368, 19.818663392)) {
                 is NetworkResponse.Success -> {
                      response.body
                 }
                 is NetworkResponse.ApiError -> {
                     response.body.orEmpty()
                 }
                is NetworkResponse.NetworkError -> {
                    "network"
                }
                else -> { "idk bro" }
             }
        }
    }
}