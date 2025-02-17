package milori.junis.weather.data.model.forecast


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Clouds(
    val all: Int
)