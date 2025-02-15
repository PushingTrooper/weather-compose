package milori.junis.weather.data

import milori.junis.weather.BuildConfig
import milori.junis.weather.data.helpers.GenericResponse
import milori.junis.weather.data.helpers.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/weather?lat={lat}&lon={lon}&appid=${BuildConfig.OPEN_WEATHER_API_KEY}")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double
    ): NetworkResponse<String, String>
}