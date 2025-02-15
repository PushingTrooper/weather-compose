package milori.junis.weather.data.model.forecast_16_days


data class ForecastResponse(
    val city: City,
    val cod: String,
    val message: Double,
    val cnt: Int,
    val list: List<WeatherForecast>
)