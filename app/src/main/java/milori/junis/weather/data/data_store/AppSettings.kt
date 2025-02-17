package milori.junis.weather.data.data_store

import kotlinx.serialization.Serializable
import milori.junis.weather.data.model.current_weather.CurrentWeatherResponse
import milori.junis.weather.data.model.unit.Unit
import milori.junis.weather.R
import milori.junis.weather.data.model.forecast.ForecastResponse
import milori.junis.weather.data.model.unit.DEFAULT_UNIT

@Serializable
data class AppSettings(
    val unit: Unit = DEFAULT_UNIT,
    val currentWeatherResponse: CurrentWeatherResponse? = null,
    val forecastResponse: ForecastResponse? = null
)