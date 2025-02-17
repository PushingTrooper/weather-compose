package milori.junis.weather.data

import milori.junis.weather.BuildConfig
import milori.junis.weather.data.helpers.GenericResponse
import milori.junis.weather.data.model.current_weather.CurrentWeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY
    ): GenericResponse<CurrentWeatherResponse>

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY
    ): GenericResponse<milori.junis.weather.data.model.forecast.ForecastResponse>
}