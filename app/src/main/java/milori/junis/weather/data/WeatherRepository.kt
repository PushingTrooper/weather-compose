package milori.junis.weather.data

import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherService: WeatherService
) {
    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ) = weatherService.getWeather(latitude, longitude)
}