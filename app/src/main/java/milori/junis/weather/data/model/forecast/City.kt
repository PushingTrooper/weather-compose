package milori.junis.weather.data.model.forecast


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id: Int,
    val name: String,
    val coord: Coord,
    val country: String,
    val population: Int,
    val timezone: Int,
    val sunrise: Int,
    val sunset: Int
)