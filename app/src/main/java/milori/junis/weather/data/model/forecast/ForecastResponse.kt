package milori.junis.weather.data.model.forecast

import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    val cod: String,
    val message: Int,
    val cnt: Int,
    val list: List<Forecast>,
    val city: City
)