package milori.junis.weather.data

import milori.junis.weather.BuildConfig
import milori.junis.weather.data.helpers.GenericResponse
import milori.junis.weather.data.model.current_weather.CurrentWeatherResponse
import milori.junis.weather.data.model.forecast_16_days.ForecastResponse
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

    @GET("data/2.5/forecast/daily?lat=44.34&lon=10.99&cnt=7&appid={API key}")
    suspend fun getForecast(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("units") units: String,
        @Query("appid") apiKey: String = BuildConfig.OPEN_WEATHER_API_KEY
    ): GenericResponse<ForecastResponse>
}