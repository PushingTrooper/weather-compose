package milori.junis.weather.data.model.current_weather

import kotlinx.serialization.Serializable


@Serializable
data class CurrentWeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind?,
    val rain: Rain?,
    val clouds: Clouds?,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)