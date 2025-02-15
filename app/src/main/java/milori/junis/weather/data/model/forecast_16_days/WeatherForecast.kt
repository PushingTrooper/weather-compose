package milori.junis.weather.data.model.forecast_16_days


import com.google.gson.annotations.SerializedName

data class WeatherForecast(
    val dt: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    @SerializedName("feels_like")
    val feelsLike: FeelsLike,
    val pressure: Int,
    val humidity: Int,
    val weather: List<Weather>,
    val speed: Double,
    val deg: Int,
    val gust: Double,
    val clouds: Int,
    val pop: Double,
    val rain: Double
)