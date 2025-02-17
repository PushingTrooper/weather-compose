package milori.junis.weather.data.model.forecast


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Forecast(
    val dt: Long,
    val main: Main,
    val weather: List<Weather>,
    val clouds: Clouds?,
    val wind: Wind?,
    val visibility: Int,
    val pop: Int,
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String
)