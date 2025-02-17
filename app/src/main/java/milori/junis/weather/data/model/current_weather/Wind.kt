package milori.junis.weather.data.model.current_weather


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Wind(
    val speed: Double,
    val deg: Int,
    val gust: Double
)