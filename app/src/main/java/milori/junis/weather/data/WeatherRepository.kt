package milori.junis.weather.data

import androidx.datastore.core.DataStore
import kotlinx.coroutines.flow.first
import milori.junis.weather.data.data_store.AppSettings
import milori.junis.weather.data.model.current_weather.CurrentWeatherResponse
import milori.junis.weather.data.model.forecast.ForecastResponse
import milori.junis.weather.data.model.unit.Unit
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService,
    private val dataStore: DataStore<AppSettings>
) {
    suspend fun getOnlineWeather(
        latitude: Double,
        longitude: Double
    ) = weatherService.getCurrentWeather(
        latitude,
        longitude,
        units = if (getUserSelectedUnit().id == 1) "metric" else "imperial"
    )

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ) = weatherService.getForecast(
        latitude,
        longitude,
        units = if (getUserSelectedUnit().id == 1) "metric" else "imperial"
    )

    suspend fun getAppSettings() = dataStore.data

    suspend fun getUserSelectedUnit() = dataStore.data.first().unit

    suspend fun saveUserSelectedUnit(unit: Unit) {
        dataStore.updateData {
            it.copy(unit = unit)
        }
    }

    suspend fun getOfflineWeatherData() = dataStore.data.first().currentWeatherResponse

    suspend fun saveWeatherData(currentWeatherResponse: CurrentWeatherResponse) {
        dataStore.updateData {
            it.copy(currentWeatherResponse = currentWeatherResponse)
        }
    }

    suspend fun getOfflineForecast() = dataStore.data.first().forecastResponse

    suspend fun saveForecast(forecastResponse: ForecastResponse) {
        dataStore.updateData {
            it.copy(forecastResponse = forecastResponse)
        }
    }
}