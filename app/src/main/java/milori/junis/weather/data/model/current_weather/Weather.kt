package milori.junis.weather.data.model.current_weather


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)