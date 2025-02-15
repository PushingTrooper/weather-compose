package milori.junis.weather.data

import milori.junis.weather.BuildConfig
import retrofit2.http.Query
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ) = weatherService.getCurrentWeather(latitude, longitude)

    suspend fun getForecast(
        latitude: Double,
        longitude: Double
    ) = weatherService.getCurrentWeather(latitude, longitude)
}